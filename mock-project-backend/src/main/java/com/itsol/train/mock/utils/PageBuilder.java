package com.itsol.train.mock.utils;

import com.itsol.train.mock.dto.BaseDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageBuilder {
    public static Pageable buildPageable(BaseDto obj) {
        Pageable pageable = null;
        if (DataUtil.isNotNullAndEmptyString(obj.getSort())) {
            pageable = PageRequest.of(obj.getPage(), obj.getPageSize(), null);
        } else {
            String[] sorts = obj.getSort().split(",");
            Sort sort = new Sort(Sort.Direction.fromString(sorts[1]), sorts[0]);
            pageable = PageRequest.of(obj.getPage(), obj.getPageSize(), sort);
        }
        return pageable;
    }
}

