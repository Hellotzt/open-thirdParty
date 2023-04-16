package com.codeLife.openThirdParty.infrastructure.common.enums;
public class CommEnum {
    public enum DeletedEnum {
        /**
         * 未删除
         */
        UN_DELETED("0","未删除"),
        /**
         * 已删除
         */
        DELETED("1","已删除"),
        ;
        private final String code;
        private final String msg;
        DeletedEnum(String code, String desc) {
            this.code = code;
            this.msg = desc;
        }

        public String getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
    }
}
