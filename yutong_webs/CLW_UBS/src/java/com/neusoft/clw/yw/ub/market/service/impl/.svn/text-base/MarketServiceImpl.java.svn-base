package com.neusoft.clw.yw.ub.market.service.impl;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.ub.market.ds.AccountManangerTask;
import com.neusoft.clw.yw.ub.market.service.MarketService;

public class MarketServiceImpl extends ServiceImpl implements MarketService
{

    @Override
    public int save(AccountManangerTask task) throws BusinessException 
    {
        try
        {
            int ret=dao.getCount("MarketBoard.countTask", task);
            if(ret==0){
                dao.insert("MarketBoard.add", task);
            }else{
               return  dao.update("MarketBoard.update", task);
            }
            return 1;
        }
        catch (Exception e)
        {
            throw new BusinessException(e.getMessage(),e.getCause());
        }
    }

}
