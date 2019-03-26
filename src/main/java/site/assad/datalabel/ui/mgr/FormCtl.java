package site.assad.datalabel.ui.mgr;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import site.assad.datalabel.dto.AdminInfoDTO;
import site.assad.datalabel.dto.FormStatDTO;
import site.assad.datalabel.mapper.FormDataSourceMapper;
import site.assad.datalabel.mapper.FormInstanceMapper;
import site.assad.datalabel.mapper.FormItemOptionMapper;
import site.assad.datalabel.mapper.FormMapper;
import site.assad.datalabel.po.FormDataSourcePO;
import site.assad.datalabel.po.FormInstancePO;
import site.assad.datalabel.po.FormItemOptionPO;
import site.assad.datalabel.po.FormPO;
import site.assad.datalabel.service.IFormService;
import site.assad.datalabel.util.DateUtil;
import site.assad.datalabel.util.SessionUtil;
import site.assad.datalabel.vo.FileHandleUtil;
import site.assad.datalabel.vo.FormDataSourceVO;
import site.assad.datalabel.vo.FormVO;
import site.assad.datalabel.vo.MessageVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static site.assad.datalabel.util.ConfUtil.*;
import static site.assad.datalabel.util.ConstantUtil.*;

@Controller
@RequestMapping("/form")
public class FormCtl {
    
    private static transient Logger LOGGER = LoggerFactory.getLogger(FormCtl.class);
    /**
     * MD我就是要跨域调用
     */
    @Resource
    private FormMapper formMapper;
    @Resource
    private FormItemOptionMapper formItemOptionMapper;
    @Resource
    private FormDataSourceMapper dataSourceMapper;
    @Resource
    private IFormService formService;
    @Resource
    private FormInstanceMapper formInstanceMapper;
    @Resource
    private FormDataSourceMapper formDataSourceMapper;
    
    /**
     * 新增表单
     */
    @RequestMapping("/add.do")
    public String addForm(HttpServletRequest request, ModelMap model, FormVO formVO) {
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return "index";
        }
        if (formVO == null) {
            model.put("adminInfo", adminInfo);
            return "task_manager";
        }
        //初始化PO
        FormPO po = FormPO.getInitPO();
        po.setOrgId(adminInfo.getOrgId());
        po.setTitle(formVO.getTitle());
        po.setLimitNum(formVO.getLimitNum());
        po.setDescription(formVO.getDescription());
        po.setItemType(formVO.getItemType());
        po.setItemContent(formVO.getItemContent());
        po.setType(formVO.getType());
        po.setFormStatus(FORM_STATUS_DRAFT);
        
        //插入表单数据
        formMapper.add(po);
        
        //插入表单字段选项
        List<FormItemOptionPO> itemOptionPOS = FormItemOptionPO.buildList(po.getFormId(), formVO.getItemOption());
        if (CollectionUtils.isNotEmpty(itemOptionPOS)) {
            formItemOptionMapper.batchAdd(itemOptionPOS);
        }
        
        //更新vo数据
        formVO.setCreateTime(po.getCreateTime());
        formVO.setCurNum(po.getCurNum());
        formVO.setOrgId(po.getOrgId());
        formVO.setFormStatus(po.getFormStatus());
        formVO.setFormId(po.getFormId());
        formVO.setFormStatus(FORM_STATUS_DRAFT);
        
        model.put("adminInfo", adminInfo);
        model.put("msg", MessageVO.of(MSG_SUCCESS, "标记任务保存成功！"));
        model.put("form", formVO);
        return "form_detail";
    }
    
    
    /**
     * 更新表单
     */
    @RequestMapping("/update.do")
    public String updateForm(HttpServletRequest request, ModelMap model, FormVO formVO) {
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return "index";
        }
        if (formVO == null) {
            model.put("adminInfo", adminInfo);
            return "task_manager";
        }
        MessageVO msg;
        
        //发布字段校验
        if (FORM_STATUS_PUBISH.equals(formVO.getFormStatus()) && !FormVO.publishValid(formVO)) {
            msg = MessageVO.of(MSG_NOTIFY, "任务字段缺失，请填写剩余表单信息后提交发布");
            model.put("adminInfo", adminInfo);
            model.put("msg", msg);
            model.put("form", formVO);
            return "form_detail";
        }
        
        //构建表单po
        FormPO po = formMapper.selectById(formVO.getFormId());
        if (po == null) {
            model.put("adminInfo", adminInfo);
            return "task_manager";
        }
        po.setOrgId(adminInfo.getOrgId());
        po.setTitle(formVO.getTitle());
        po.setItemType(formVO.getItemType());
        po.setLimitNum(formVO.getLimitNum());
        po.setFormStatus(formVO.getFormStatus());
        po.setDescription(formVO.getDescription());
        po.setItemContent(formVO.getItemContent());
        po.setType(formVO.getType());
        
        //发布表单校验,数据源校验
        if (FORM_STATUS_PUBISH.equals(po.getFormStatus())) {
            if (po.getSourceCount() == 0) {
                msg = MessageVO.of(MSG_NOTIFY, "没有关联的数据源，请添加数据源后发布");
                po.setFormStatus(FORM_STATUS_DRAFT);
            } else if (po.getLimitNum() <= po.getCurNum()) {
                msg = MessageVO.of(MSG_NOTIFY, "限制任务数量小于发布任务数量");
            } else {
                msg = MessageVO.of(MSG_SUCCESS, "标记任务发布成功！");
            }
        } else {
            msg = MessageVO.of(MSG_SUCCESS, "标记任务保存成功！");
        }
        
        //更新表单po
        formMapper.update(po);
        
        //更新表单字段选项
        List<FormItemOptionPO> itemOptionPOS = FormItemOptionPO.buildList(po.getFormId(), formVO.getItemOption());
        if (CollectionUtils.isNotEmpty(itemOptionPOS)) {
            formItemOptionMapper.deleteByFormId(po.getFormId());
            formItemOptionMapper.batchAdd(itemOptionPOS);
        }
        
        //更新vo数据
        formVO.setCreateTime(po.getCreateTime());
        formVO.setCurNum(po.getCurNum());
        formVO.setOrgId(po.getOrgId());
        formVO.setFormStatus(po.getFormStatus());
        formVO.setFormId(po.getFormId());
        
        model.put("adminInfo", adminInfo);
        model.put("msg", msg);
        model.put("form", formVO);
        return "form_detail";
    }
    
    
    /**
     * 文件上传, ajax
     * -1 : 文件过大，-2：文件名不正确, -3：文件为空，-4: 文件格式不支持，-5：文件解析失败
     */
    @ResponseBody
    @RequestMapping("/upload.do")
    public String addDataSource(@RequestParam("uploadFile") MultipartFile file, Integer type, String formId) {
        try {
            if (file.getSize() > UPLOAD_FILE_SIZE_LIMIT) {
                return "-1";
            }
            String c = file.getOriginalFilename();
            if (!file.getOriginalFilename().endsWith("zip")) {
                return "-2";
            }
            
            //文件暂存
            byte[] bytes = file.getBytes();
            //创建临时上传子目
            String uploadDir = UPLOAD_PATH + "/" + UUID.randomUUID().toString().substring(0, 15) + "/";
            //资源目录
            String assertDir = ASSERT_PATH + "/" + UUID.randomUUID().toString().substring(0, 15) + "/";
            FileUtils.forceMkdir(new File(uploadDir));
            FileUtils.forceMkdir(new File(assertDir));
            
            String uploadPath = uploadDir + file.getOriginalFilename();
            Path path = Paths.get(uploadPath);
            Files.write(path, bytes);
            
            //文件解压
            File zip = new File(uploadPath);
            List<String> files = FileHandleUtil.unZip(zip, uploadDir);
            if (CollectionUtils.isEmpty(files)) {
                return "-3";
            }
            //删除zip
            zip.delete();
            
            Date now = new Date();
            
            //时间不足，我就不组件化分层实现了
            List<FormDataSourcePO> dataSourcePOS = new ArrayList<>();
            //文件处理，仅支持 txt，只读取第一个文本
            if (FORM_TYPE_TEXT.equals(type)) {
                if (!files.get(0).endsWith("txt")) {
                    return "-4";
                }
                File txtFile = new File(files.get(0));
                //逐行扫描
                Scanner scanner = new Scanner(new FileInputStream(uploadDir + txtFile));
                int sort = 1;
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (StringUtils.isEmpty(line.trim()) || StringUtils.isBlank(line.trim())) {
                        continue;
                    }
                    FormDataSourcePO po = new FormDataSourcePO();
                    po.setDataSourceId(UUID.randomUUID().toString());
                    po.setFormId(formId);
                    po.setDataType(FORM_TYPE_TEXT);
                    po.setContent(line.trim());
                    po.setSort(sort++);
                    po.setCreateTime(now);
                    dataSourcePOS.add(po);
                }
            }
            //图片处理，仅支持 png，jpg
            else if (FORM_TYPE_PICTURE.equals(type)) {
                List<String> legalFiles = files.stream().filter(e -> (e.endsWith("png") || e.endsWith("jpg")) && !e.startsWith("_")).collect(Collectors.toList());
                List<String> illegalFiles = (List<String>) CollectionUtils.subtract(files, legalFiles);
                if (CollectionUtils.isEmpty(legalFiles)) {
                    return "-4";
                }
                int sort = 1;
                for (String filePath : legalFiles) {
                    FormDataSourcePO po = new FormDataSourcePO();
                    po.setDataSourceId(UUID.randomUUID().toString());
                    po.setFormId(formId);
                    po.setSort(sort++);
                    po.setDataType(FORM_TYPE_PICTURE);
                    po.setCreateTime(now);
                    po.setPath(assertDir + filePath);
                    dataSourcePOS.add(po);
                }
                //资源复制
                for (String filename : legalFiles) {
                    FileUtils.copyFile(new File(uploadDir + filename), new File(assertDir + filename));
                }
            }
            //音频处理，仅支持 mp3
            else if (FORM_TYPE_VOICE.equals(type)) {
                List<String> legalFiles = files.stream().filter(e -> e.endsWith("mp3") && !e.startsWith("_")).collect(Collectors.toList());
                List<String> illegalFiles = (List<String>) CollectionUtils.subtract(files, legalFiles);
                if (CollectionUtils.isEmpty(legalFiles)) {
                    return "-4";
                }
                int sort = 1;
                for (String filePath : legalFiles) {
                    FormDataSourcePO po = new FormDataSourcePO();
                    po.setDataSourceId(UUID.randomUUID().toString());
                    po.setDataType(FORM_TYPE_VOICE);
                    po.setFormId(formId);
                    po.setSort(sort++);
                    po.setCreateTime(now);
                    po.setPath(assertDir + filePath);
                    dataSourcePOS.add(po);
                }
                
                //资源复制
                for (String filename : legalFiles) {
                    FileUtils.copyFile(new File(uploadDir + filename), new File(assertDir + filename));
                }
            } else {
                return "-4";
            }
            //删除解压文件
            FileUtils.forceDelete(new File(uploadDir));
            //插入数据源数据
            if (CollectionUtils.isNotEmpty(dataSourcePOS)) {
                dataSourceMapper.batchAdd(dataSourcePOS);
            }
            //更新表单数据源
            formMapper.updateSourceCount(dataSourcePOS.size(), formId);
            
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return "-5";
        }
        
        return "1";
    }
    
    /**
     * 获取数据源数据，ajax
     */
    @ResponseBody
    @RequestMapping("/getDataSource.do")
    public String getDataSourceList(String formId) {
        List<FormDataSourcePO> pos = dataSourceMapper.selectByFormId(formId);
        List<FormDataSourceVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(pos)) {
            vos = pos.stream()
                    .map(FormDataSourceVO::of)
                    .sorted(Comparator.comparing(FormDataSourceVO::getSort))
                    .collect(Collectors.toList());
        }
        return JSON.toJSONString(vos);
    }
    
    
    /**
     * 下载表单数据，组装为csv文件
     */
    @ResponseBody
    @RequestMapping("/downloadData.do")
    public String downloadDate(String formId, HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(formId)) {
            return "文件下载失败，任务已经被删除";
        }
        
        //查询表单
        FormPO formPO = formMapper.selectById(formId);
        
        //查询选项字段
        List<FormItemOptionPO> optionPOS = formItemOptionMapper.selectByFormId(formPO.getFormId());
        //选项字典
        Map<String, String> optionMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(optionPOS)) {
            optionMap = optionPOS.stream().collect(Collectors.toMap(FormItemOptionPO::getItemOptionId, FormItemOptionPO::getContent));
        }
        //查询表单数据源
        List<FormDataSourcePO> dataSourcePOS = formDataSourceMapper.selectByFormId(formPO.getFormId());
        //数据源字典
        Map<String, FormDataSourcePO> dataSourcePOMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(dataSourcePOS)) {
            dataSourcePOMap = dataSourcePOS.stream().collect(Collectors.toMap(FormDataSourcePO::getDataSourceId, Function.identity()));
        }
        
        //查询表单实例
        List<FormInstancePO> instancePOS = formInstanceMapper.selectByFormId(formPO.getFormId());
        
        //组装DTO列表
        List<FormStatDTO> statDTOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(instancePOS)) {
            for (FormInstancePO instancePO : instancePOS) {
                FormStatDTO statDTO = new FormStatDTO();
                statDTO.setFormTitle(formPO.getTitle());
                statDTO.setCreateTime(instancePO.getCreateTime());
                //组装资源项
                FormDataSourcePO dataSourcePO = dataSourcePOMap.get(instancePO.getDataSourceId());
                if (FORM_TYPE_TEXT.equals(formPO.getType())) {
                    statDTO.setDataSourceContent(dataSourcePO.getContent());
                } else {
                    statDTO.setDataSourceContent(dataSourcePO.getPath());
                }
                //组装选项
                if (ITEM_TYPE_TEXT.equals(formPO.getItemType())) {
                    statDTO.setItemContent(instancePO.getItemContent());
                } else {
                    statDTO.setItemContent(optionMap.get(instancePO.getItemContent()));
                }
                statDTOS.add(statDTO);
            }
        }
        
        //写入CSV
        List<String> csvHeader = Arrays.asList("任务标题", "数据源描述", "任务实例字段", "标注时间");
        CSVFormat formator = CSVFormat.DEFAULT;
        String filePath = TMP_PATH + "/" + formPO.getTitle() + UUID.randomUUID().toString().substring(12) + ".csv";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            CSVPrinter printer = new CSVPrinter(fileWriter, formator);
            //写入表头
            printer.printRecord(csvHeader);
            //写入数据
            if (CollectionUtils.isNotEmpty(statDTOS)) {
                for (FormStatDTO statDTO : statDTOS) {
                    printer.printRecord(Arrays.asList(statDTO.getFormTitle(), statDTO.getDataSourceContent(), statDTO.getItemContent(), DateUtil.formatToDateTime(statDTO.getCreateTime())));
                }
            }
            fileWriter.flush();
            printer.flush();
            fileWriter.close();
            printer.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        
        //文件写入Response
        File file = new File(filePath);
        if (!file.exists()) {
            return "文件下载失败";
        }
        try {
            FileInputStream inputStream = new FileInputStream(file);
            String prettyFileName = "data-label-result-" + UUID.randomUUID().toString().substring(0,10) + ".csv";
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + prettyFileName);
            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            return "文件下载成功";
            
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    
        return "文件下载失败";
    }
    
    
    /**
     * 删除表单
     */
    @RequestMapping("/delete.do")
    public String deleteForm(HttpServletRequest request, String formId) {
        //权限验证
        AdminInfoDTO adminInfo = SessionUtil.getAdminInfo(request);
        if (adminInfo == null) {
            return "index";
        }
        
        formMapper.delet(formId);
        
        return "redirect:/taskManager.html";
    }
    
}
