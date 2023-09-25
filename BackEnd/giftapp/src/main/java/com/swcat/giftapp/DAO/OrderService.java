package com.swcat.giftapp.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import com.swcat.giftapp.Entities.addon;
import com.swcat.giftapp.Entities.giftpack;
import com.swcat.giftapp.Entities.gorder;
import com.swcat.giftapp.Entities.gorderAddons;
import com.swcat.giftapp.Entities.orderProcess;
import com.swcat.giftapp.JpaRepo.addonRepo;
import com.swcat.giftapp.JpaRepo.giftpackRepo;
import com.swcat.giftapp.JpaRepo.gorderAddonsRepo;
import com.swcat.giftapp.JpaRepo.gorderRepo;
import com.swcat.giftapp.JpaRepo.orderProcessRepo;
import com.swcat.giftapp.Models.OrderUserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
    @Autowired
    private gorderRepo gorderRepo;

    @Autowired
    private gorderAddonsRepo gorderAddonsRepo;

    @Autowired
    private orderProcessRepo orderProcessRepo;

    @Autowired
    private giftpackRepo giftpackRepo;

    @Autowired
    private addonRepo addonRepo;

    @Autowired
    private MailService mailService;

    public OrderUserModel createOrder(OrderUserModel orderModel){
        //Note: đây là db fake và sẽ được xóa
        //fakeDBGiftpackAndAddOn();


        //Lưu Order vào csdl
        orderModel.setDateOrder(convertDateToString(new Date()));
        gorder gorder = convertOrderModelToOrderEntity(orderModel);        
        OrderUserModel order = convertOrderEntityToOrderModel(gorderRepo.save(gorder));
        //Lưu vào orderProcess
        orderProcess orderProcess = new orderProcess(order.getCid(), "Đang chờ xử lý", 0, 0);
        orderProcessRepo.save(orderProcess);
        order.setStatus("Đang chờ xử lý");
        //có sử dụng thêm addon
        if(orderModel.getAddonId().isEmpty() == false){

            for(int i=0; i<orderModel.getAddonId().size(); i++){
            gorderAddons addon = new gorderAddons(null, 
            order.getCid(), 
            orderModel.getAddonId().get(i), 
            orderModel.getContent().get(i));            
            addon = gorderAddonsRepo.save(addon);

            }
            
            //thêm thông tin gorderaddon vào order
            // List<gorderAddons> addOns = gorderAddonsRepo.findByGorderId(order.getCid());
            // order.setAddonId(addOns.stream().map((addOn) ->{
            //     return addOn.getAddonId();
            // }).collect(Collectors.toList()));
            // order.setContent(addOns.stream().map((addOn) ->{
            //     return addOn.getContent();
            // }).collect(Collectors.toList()));
        }

        

        order.setCost(caculateCostInOrder(order.getCid()));
        try {
            mailService.sendEmailToCustomer(order, order.getUname());
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return order;

    }


    // public void updateOrder(OrderModel orderModel){
    //     //Lưu Order vào csdl
    //     gorder gorder = convertOrderModelToOrderEntity(orderModel);
    //     gorderRepo.save(gorder);
    //     //có sử dụng thêm addon
    //     if(orderModel.getAddonId() != null){
    //         gorderAddons addon = gorderAddonsRepo.findByGorderId(orderModel.getCid());
    //         addon.setAddonId(orderModel.getAddonId());
    //         addon.setContent(orderModel.getContent());

    //         gorderAddonsRepo.save(addon);
    //     }        
    // }

    public List<OrderUserModel> getAllOrder(){
        List<OrderUserModel> orderModels = gorderRepo.findAll()
        .stream()
        .map(order -> {
            OrderUserModel orderModel = convertOrderEntityToOrderModel(order);
            
            //Load trạng thái đơn hàng

            Optional<orderProcess> process = orderProcessRepo.findById(order.getCid());
            orderModel.setStatus(process.get().getPstate());

            //Load Addon đơn hàng
            Boolean isGOrderAddOnExist = gorderAddonsRepo.existsByGorderId(order.getCid());
            if(isGOrderAddOnExist){
                List<gorderAddons> addOns = gorderAddonsRepo.findByGorderId(order.getCid());
            orderModel.setAddonId(addOns.stream().map((addOn) ->{
                return addOn.getAddonId();
            }).collect(Collectors.toList()));
            orderModel.setContent(addOns.stream().map((addOn) ->{
                return addOn.getContent();
            }).collect(Collectors.toList()));
            }

            orderModel.setCost(caculateCostInOrder(order.getCid()));
            return orderModel;
        })
        .collect(Collectors.toList());

        return orderModels;
    }

    public OrderUserModel getOrderById(int cid){
        Optional<gorder> orderEntity = gorderRepo.findById(cid);
        OrderUserModel orderModel = convertOrderEntityToOrderModel(orderEntity.get());
        //Load trạng thái đơn hàng
        
        Optional<orderProcess> process = orderProcessRepo.findById(cid);
        orderModel.setStatus(process.get().getPstate());
        
        //Load Addon đơn hàng
        Boolean isGOrderAddOnExist = gorderAddonsRepo.existsByGorderId(cid);
        if(isGOrderAddOnExist){
            List<gorderAddons> addOns = gorderAddonsRepo.findByGorderId(cid);
            orderModel.setAddonId(addOns.stream().map((addOn) ->{
                return addOn.getAddonId();
            }).collect(Collectors.toList()));
            orderModel.setContent(addOns.stream().map((addOn) ->{
                return addOn.getContent();
            }).collect(Collectors.toList()));
        }
        orderModel.setCost(caculateCostInOrder(cid));
        return orderModel;
    }


    public List<OrderUserModel> getOrderByUserName(String uname){
        List<OrderUserModel> orders = gorderRepo.findByUname(uname)
        .stream()
        .map(order -> {
            OrderUserModel orderModel = convertOrderEntityToOrderModel(order);
        //Load trạng thái đơn hàng

        Optional<orderProcess> process = orderProcessRepo.findById(order.getCid());
        orderModel.setStatus(process.get().getPstate());

        //Load Addon đơn hàng
        Boolean isGOrderAddOnExist = gorderAddonsRepo.existsByGorderId(order.getCid());
        if(isGOrderAddOnExist){
            List<gorderAddons> addOns = gorderAddonsRepo.findByGorderId(order.getCid());
            orderModel.setAddonId(addOns.stream().map((addOn) ->{
                return addOn.getAddonId();
            }).collect(Collectors.toList()));
            orderModel.setContent(addOns.stream().map((addOn) ->{
                return addOn.getContent();
            }).collect(Collectors.toList()));
        }

        orderModel.setCost(caculateCostInOrder(orderModel.getCid()));
        return orderModel;
        })
        .collect(Collectors.toList());

        return orders;
    }

    


    public OrderUserModel convertOrderEntityToOrderModel(gorder order){
        OrderUserModel orderModel = new OrderUserModel
        (order.getCid(), 
        order.getUname(), 
        order.getGiftpackId(), 
        order.getReceiveAddress(), 
        order.getPhoneNumber(), 
        convertDateToString(order.getDateOrder()), 
        convertDateToString(order.getDateArrive()), 
        order.getGreetingCardContent(), 
        null, null, null, 0);

        return orderModel;
    }

    public gorder convertOrderModelToOrderEntity(OrderUserModel orderModel){
        gorder order  = new gorder(orderModel.getCid(), 
        orderModel.getUname(), 
        orderModel.getGiftpackId(), 
        orderModel.getReceiveAddress(), 
        orderModel.getPhoneNumber(), 
        convertStringToDate(orderModel.getDateOrder()), 
        convertStringToDate(orderModel.getDateArrive()), 
        orderModel.getGreetingCardContent());

        return order;

    }


    public boolean isOrderExist(int id){
        return gorderRepo.existsById(id);
    }

    public String convertDateToString(Date date){
        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }

    public Date convertStringToDate(String dateString){
        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    public Date formateDate(Date date){
        String dateString = convertDateToString(date);
        Date dateFormat = convertStringToDate(dateString);
        return dateFormat;
    }

    public int caculateCostInOrder(int orderId){
        int cost = 0;

        //Tính giá trị giftpack
        gorder order = gorderRepo.findById(orderId).get();

        int costGiftPack = giftpackRepo.findById(order.getGiftpackId()).get().getPrice();
        cost+= costGiftPack;

        //Tính giá trị addOn

        Boolean isGOrderAddOnExist = gorderAddonsRepo.existsByGorderId(order.getCid());
        if(isGOrderAddOnExist){
            List<gorderAddons> addOns = gorderAddonsRepo.findByGorderId(orderId);
            for(gorderAddons addOn : addOns){
                int costAddOn = addonRepo.findById(addOn.getAddonId()).get().getPrice();
                cost+=costAddOn;
            }  
        }
        
        return cost;

    }

    public void fakeDBGiftpackAndAddOn(){
        addon fakeAddOn = new addon(null, "fakeAddOn1", "Đây là addOnFake", 30000);
        addonRepo.save(fakeAddOn);

        giftpack fakeGiftpack = new giftpack(null, "fakeGiftpack1", "Đây là giftpack fake", "thumbnailimg", 100000, 5, 1);
        giftpackRepo.save(fakeGiftpack);
    }

    
}
