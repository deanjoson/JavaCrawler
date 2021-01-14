drop database if exists db_java_crawler;
create database db_java_crawler;


DROP TABLE IF EXISTS db_java_crawler.t_fund;
CREATE TABLE IF NOT EXISTS db_java_crawler.t_fund
(
    fund_code      varchar(10) NOT NULL COMMENT '基金代码',
    fund_name      varchar(64) not null comment '基金名称',
    original_rate  double      not null default 0 comment '原始费率',
    purchase_rate  double      not null default 0 comment '购买费率',
    min_sub_amount int         not null default 0 comment '最小申购金额',
    syl_1y         double      not null default 0 comment '近一月收益率',
    syl_3y         double      not null default 0 comment '近三月收益率',
    syl_6y         double      not null default 0 comment '近6月收益率',
    syl_1n         double      not null default 0 comment '近一年收益率',
    manager_code   varchar(10) not null default '' comment '现任基金经理代码',
    manager_name   varchar(64) not null default '' comment '现任基金经理',
    create_time    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (fund_code)
    ) ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '基金信息';