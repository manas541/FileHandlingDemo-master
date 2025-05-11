package com.practice.Bajaj.DTO;

public class GetResponse {
        private int operation_code;

        public GetResponse(int operation_code) {
            this.operation_code = operation_code;
        }

        public int getOperation_code() {
            return operation_code;
        }

        public void setOperation_code(int operation_code) {
            this.operation_code = operation_code;
        }

}
