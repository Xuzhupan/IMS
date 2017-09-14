package wudeng.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import wudeng.entity.Goods;
import wudeng.service.GoodsService;
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService{

	public boolean exist(Goods goods) {
		List<Goods> list = this.baseDao.find("from Goods where name =\'"+goods.getName()+"\' and type=\'"+goods.getType()+"\' and unit=\'"+goods.getUnit()+"\'");
		if(list.size()>0){
			return true;
		}
		return false;
	}

}
