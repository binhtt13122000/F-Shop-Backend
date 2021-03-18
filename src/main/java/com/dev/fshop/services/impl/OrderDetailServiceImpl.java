package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Orders;
import com.dev.fshop.repositories.OrderDetailRepository;
import com.dev.fshop.services.OrderDetailService;
import com.dev.fshop.services.ProductDetailService;
import com.dev.fshop.supporters.CartDetail;
import com.dev.fshop.supporters.OrderDetail;
import com.dev.fshop.supporters.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductDetailService productDetailService;

    @Override
    @Transactional
    public boolean createOrderDetails(List<CartDetail> cartDetailList, Orders orders) {
        for (CartDetail cartDetail : cartDetailList) {
            ProductDetail productDetail = productDetailService.getProductDetailByProductIdAndProductSize(cartDetail.getProduct().getProductId(),
                    cartDetail.getCartSize(), 0);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(productDetail.getProduct());
            orderDetail.setOrders(orders);
            orderDetail.setOrderSize(productDetail.getProSize());
            orderDetail.setOrderItemQuan(cartDetail.getCartQuantity());
            orderDetail.setOrderItemPrice(cartDetail.getCartItemPrice());
            orderDetail.setCreateAt(new Date());
            orderDetail.setStatus(1);
            orderDetailRepository.save(orderDetail);
            productDetailService.updateQuantityProductDetail(productDetail, cartDetail.getCartQuantity());
        }
        return true;
    }
}
