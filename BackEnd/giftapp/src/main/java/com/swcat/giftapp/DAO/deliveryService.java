package com.swcat.giftapp.DAO;

import java.util.List;
import java.util.stream.Collectors;

import com.swcat.giftapp.Entities.orderProcess;
import com.swcat.giftapp.JpaRepo.orderProcessRepo;
import com.swcat.giftapp.Models.delivery.orderProcessModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class deliveryService {
    @Autowired
    private orderProcessRepo orderPrRepo;






    public orderProcessModel addOrderProcessing(orderProcessModel orderProcessModel)
    {
        orderProcess orderProcess=ConvertOrderModeltoOrderProcess(orderProcessModel);
        return ConvertOrderProcessToOrderProcessModel(orderPrRepo.save(orderProcess));
    }
    //Change stage of shipment based on gorderid
    /**
     * 
     * @param order
     * @param stateupdate have value waitingcomfirm,shipping,done
     * @return sucess or unsucess
     */
    public void changeState(orderProcessModel order,String stateupdate)
    {
        orderProcess orderProcess=orderPrRepo.findById(order.getGorderId()).get();
        if(orderProcess!=null)
        {
            orderProcess.setPstate(stateupdate);
            orderPrRepo.save(orderProcess);
        }
        else
        {

        }
    }
    /**
     * pdeliveried MUST BE 1 to view order have delivered
     */
    public List<orderProcessModel> listOrderProcessDeliveried(int pdelivered)
    {
        List<orderProcess> orderProcesses = orderPrRepo.findByPdelivered(pdelivered);
        List<orderProcessModel> orderProcessModels=orderProcesses.stream()
        .map(orderProcess->ConvertOrderProcessToOrderProcessModel(orderProcess))
        .collect(Collectors.toList()); 
        return orderProcessModels;
    }


    /**
     * pdeliveried MUST BE 1 to view order have delivered
     */
    public List<orderProcessModel> listOrderProcessCanceled(int pcanceled)
    {
        List<orderProcess> orderProcesses = orderPrRepo.findByPcanceled(pcanceled);
        List<orderProcessModel> orderProcessModels=orderProcesses.stream()
        .map(orderProcess->ConvertOrderProcessToOrderProcessModel(orderProcess))
        .collect(Collectors.toList()); 
        return orderProcessModels;
    }


    public List<orderProcessModel> listOrderProcess()
    {
        List<orderProcess> orderProcesses = orderPrRepo.findAll();
        List<orderProcessModel> orderProcessModels=orderProcesses.stream()
        .map(orderProcess->ConvertOrderProcessToOrderProcessModel(orderProcess))
        .collect(Collectors.toList()); 
        return orderProcessModels;
    }
    /**
     * 
     * @param id
     * @return orderProcessModel we can find
     */
    public orderProcessModel getOrderProcessbyID(int id)
    {
        orderProcess orderProcess=orderPrRepo.findById(id).orElse(null);
        orderProcessModel orderProcessModel=ConvertOrderProcessToOrderProcessModel(orderProcess);
        return orderProcessModel ;
    }
    public void deleteOrderProcess(int orderId){
            orderPrRepo.deleteById(orderId);
    }
    
    public void updateOrderProcess(orderProcessModel orderProcessModel)
    {
        orderProcess orderProcessupdate=ConvertOrderModeltoOrderProcess(orderProcessModel);
        orderPrRepo.save(orderProcessupdate);
    }


    public boolean ispOrderProcessExist(int orderId)
    {
        return orderPrRepo.existsById(orderId);
    }

    public orderProcessModel ConvertOrderProcessToOrderProcessModel(orderProcess orderProcess)
    {
        orderProcessModel orderProcessModel=new orderProcessModel(orderProcess.getGorderId(),orderProcess.getPstate(),orderProcess.getPdelivered(),orderProcess.getPcanceled());
        return orderProcessModel;
    }
    public orderProcess ConvertOrderModeltoOrderProcess(orderProcessModel orderProcessModel)
    {
        orderProcess orderProcess=new orderProcess(orderProcessModel.getGorderId(),orderProcessModel.getPstate(), orderProcessModel.getPdelivered(), orderProcessModel.getPcanceled());
        return orderProcess;
    }
}
