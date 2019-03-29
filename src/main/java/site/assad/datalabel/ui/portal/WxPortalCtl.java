package site.assad.datalabel.ui.portal;


import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static site.assad.datalabel.util.ConfUtil.WX_APP_ID;
import static site.assad.datalabel.util.ConfUtil.WX_APP_SECRET;

@RestController
@RequestMapping("/portal")
public class WxPortalCtl {
    
    private static transient Logger LOGGER = LoggerFactory.getLogger(WxPortalCtl.class);
    
    /**
     * 微信用户登录，获取 openId
     */
    @RequestMapping(value = "/wxLogin.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String wxLogin(@RequestBody JSONObject jsonParam){
        String code = jsonParam.getString("code");
        //访问微信api获取openid
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WX_APP_ID + "&secret=" + WX_APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
