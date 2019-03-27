create table wx_user
(
  user_id     char(36)    not null,
  wx_id       varchar(40) not null,
  wx_name     varchar(80),
  create_time datetime,
  primary key (user_id),
  key IDX_WX_ID (wx_id) using btree
) engine = innodb
  default charset = utf8 comment '微信用户表';


create table admin_user
(
  admin_id    char(36)    not null,
  org_id      char(36)    not null,
  user_name   varchar(50) not null,
  pwd         varchar(50) not null,
  create_time datetime,
  primary key (admin_id),
  key IDX_ORG_ID (org_id) using btree
) engine = innodb
  default charset = utf8 comment '管理员表';


create table user_task
(
  id               char(36) not null,
  user_id          char(36) not null,
  form_id          char(36) not null,
  org_id           char(36) not null,
  task_status      tinyint           default 1 comment '完成状态，1-未进行，2-进行中，3-已完成',
  data_source_sort smallint unsigned default 1 comment '目前任务下标',
  create_time      datetime,
  primary key (id),
  key IDX_USER_ID (user_id) using btree,
  key IDX_FORM_ID (form_id) using btree,
  key IDX_ORG_ID (org_id) using btree
) engine = innodb
  default charset = utf8 comment '用户任务表';


create table form_template
(
  template_id  char(36)     not null,
  title        varchar(100) not null,
  description  text         default null comment '模版描述',
  type         tinyint      default null comment '表单类型：1-文本标注，2-图片标注，3-音频标注',
  item_type    tinyint      default null comment '字段类型：1-填写型，2-选择型',
  item_content varchar(250) default null comment '字段内容',
  create_time  datetime,
  primary key (template_id)
) engine = innodb
  default charset = utf8 comment '表单模版表';


create table form
(
  form_id      char(36)     not null,
  org_id       char(36)     not null,
  title        varchar(100) not null,
  description  text              default null comment '表单描述',
  cur_num      smallint unsigned default 0 comment '表单填单数量',
  limit_num    smallint unsigned default 0 comment '表单填写上限',
  source_count smallint unsigned default 0 comment '数据源数量',
  form_status       tinyint           default 0 comment '表单状态：0-草稿，1-发布，2-关闭',
  type         tinyint           default null comment '表单类型：1-文本标注，2-图片标注，3-音频标注',
  item_type    tinyint           default null comment '字段类型：1-填写型，2-选择型',
  item_content varchar(250)      default null comment '字段内容',
  create_time  datetime,
  primary key (form_id),
  key IDX_ORG_ID (org_id) using btree
) engine = innodb
  default charset = utf8 comment '表单主表';


create table form_item_option
(
  item_option_id char(36) not null,
  form_id        char(36) not null,
  content        varchar(250) default null comment '字段内容',
  sort           tinyint      default 1 comment '选项排序下标',
  create_time    datetime,
  primary key (item_option_id),
  key IDX_FORM_ID (form_id) using btree
) engine = innodb
  default charset = utf8 comment '字段选项定义表';


create table form_data_source
(
  data_source_id char(36) not null,
  form_id        char(36) not null,
  data_type           tinyint          default null comment '数据类型：1-文本标注，2-图片标注，3-音频标注',
  content        text             default null comment '文本型内容',
  path           varchar(200)     default null comment '文件型url',
  sort           tinyint unsigned default 1 comment '任务源排序',
  create_time    datetime,
  primary key (data_source_id),
  key IDX_FORM_ID (form_id) using btree
) engine = innodb
  default charset = utf8 comment '表单数据源表';


create table form_instance
(
  id             char(36) not null,
  user_id        char(36) not null,
  form_id        char(36) not null,
  data_source_id char(36) not null,
  item_content   varchar(200) comment '字段内容/item_option_id 使用逗号分割',
  create_time    datetime,
  primary key (id),
  key IDX_USER_ID (user_id) using btree,
  key IDX_FORM_ID (form_id) using btree,
  key IDX_DATA_SOURCE_ID (data_source_id) using btree
) engine = innodb
  default charset = utf8 comment '表单实例表';
