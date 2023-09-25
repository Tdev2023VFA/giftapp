package com.swcat.giftapp.DAO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.swcat.giftapp.Entities.accountInfo;
import com.swcat.giftapp.JpaRepo.accountInfoRepo;
import com.swcat.giftapp.Models.accountInfo.accountInfoModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountInfoService {
    @Autowired
    private accountInfoRepo accountInfoRepo;

    //lưu giá trị của accountInfo
    public accountInfoModel createAccountInfo(accountInfoModel accountInfoModel){

        accountInfo accountInfoEntity = convertAccountInfoModelToAccountInfoEntity(accountInfoModel);

        return convertAccountInfoEntityToAccountInfoModel(accountInfoRepo.save(accountInfoEntity));
    }

    //update giá trị của accountInfo
    public void updateAccountInfo(accountInfoModel accountInfoModel){
        accountInfo accountInfo = convertAccountInfoModelToAccountInfoEntity(accountInfoModel);
        accountInfoRepo.save(accountInfo);
    }

    //lấy 1 giá trị accountInfo
    public accountInfoModel getAccountInfo(String uname){
        Optional<accountInfo> optional = accountInfoRepo.findById(uname);
        accountInfo accountInfo = optional.get();
        return convertAccountInfoEntityToAccountInfoModel(accountInfo);
    }

    //lấy tất cả giá trị của accountInfo
    public List<accountInfoModel> getAccountInfos(){
        List<accountInfo> accountInfos = accountInfoRepo.findAll();
        List<accountInfoModel> accountInfoModels = accountInfos.stream()
        .map(accountInfo -> convertAccountInfoEntityToAccountInfoModel(accountInfo))
        .collect(Collectors.toList());

        return accountInfoModels;
    }

    //xóa accountInfo
    public void deleteAccountInfo(String name){
        accountInfoRepo.deleteById(name);
    }

    public boolean isAccountInfoExist(String name){
        return accountInfoRepo.existsById(name);
    }

    public accountInfoModel convertAccountInfoEntityToAccountInfoModel(accountInfo accountInfo){
        accountInfoModel accountInfoModel = new accountInfoModel(accountInfo.getUname(),
        accountInfo.getDisName(),
        accountInfo.getUaddress(),
        accountInfo.getPhonenum(), accountInfo.getEmail());
        return accountInfoModel;
    }

    public accountInfo convertAccountInfoModelToAccountInfoEntity(accountInfoModel accountInfoModel){
        accountInfo accountInfo = new accountInfo(accountInfoModel.getUname(),
        accountInfoModel.getDisname(),
        accountInfoModel.getUaddress(),
        accountInfoModel.getPhonenum(), accountInfoModel.getEmail());

        return accountInfo;
    }
}
