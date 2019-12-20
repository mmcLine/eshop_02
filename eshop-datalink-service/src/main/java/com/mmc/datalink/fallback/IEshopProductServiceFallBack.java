package com.mmc.datalink.fallback;

import com.mmc.common.msg.ObjectRestResponse;
import com.mmc.datalink.feign.IEshopProductService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: mmc
 * @create: 2019-12-17 23:19
 **/
@Component
public class IEshopProductServiceFallBack implements IEshopProductService {
    @Override
    public String findBrandById(Integer id) {
        return "default";
    }

    @Override
    public ObjectRestResponse findBrandByIds(List<Integer> ids) {
        return null;
    }

    @Override
    public String findCategoryById(Integer id) {
        return null;
    }

    @Override
    public String findProductIntroById(Integer id) {
        return null;
    }

    @Override
    public String findProductPropertyById(Integer id) {
        return null;
    }

    @Override
    public String findProductPropertyByProductId(Integer productId) {
        return null;
    }

    @Override
    public String findProductById(Integer id) {
        return null;
    }

    @Override
    public String findProductSpecificationById(Integer id) {
        return null;
    }

    @Override
    public String findProductSpecificationByProductId(Integer productId) {
        return null;
    }
}
