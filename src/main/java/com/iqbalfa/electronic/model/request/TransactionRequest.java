package com.iqbalfa.electronic.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iqbalfa.electronic.model.Product;
import com.iqbalfa.electronic.model.User;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
public class TransactionRequest {
    private UserIdRequest user;
    private ProductIdRequest product;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date transactionDate;
    private Integer qty;

}
