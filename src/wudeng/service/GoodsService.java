package wudeng.service;

import wudeng.entity.Goods;

public interface GoodsService extends BaseService<Goods>{
	//判断商品是否已经存在
	public boolean exist(Goods goods);
}
