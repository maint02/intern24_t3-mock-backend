package com.itsol.train.mock.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PagingDataDTO {
    private int page;
    private int totalPage;
    private List<?> listData=new ArrayList<>();
    private int limit;
}
