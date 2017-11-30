package com.atu.erp.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.atu.erp.dao.FavoritesDao;
import com.atu.erp.domain.Favorites;
import com.atu.erp.domain.query.FavoritesQuery;

public class FavoritesDaoImpl extends SqlMapClientTemplate implements FavoritesDao {

	@Override
	public Integer insert(Favorites favorites) {
		return (Integer)insert("Favorites.insert",favorites);
	}

	@Override
	public void delete(Integer favoritesId) {
		delete("Favorites.deleteByPrimaryKey",favoritesId);
	}

	@Override
	public int countByCondition(FavoritesQuery favoritesQuery) {
		return (Integer)queryForObject("Favorites.countByCondition", favoritesQuery);
	}

	@Override
	public List<Favorites> selectByCondition(FavoritesQuery favoritesQuery) {
		return (List<Favorites>)queryForList("Favorites.selectByCondition", favoritesQuery);
	}

	@Override
	public List<Favorites> selectByConditionForPage(
			FavoritesQuery favoritesQuery) {
		return (List<Favorites>)queryForList("Favorites.selectByConditionForPage", favoritesQuery);
	}

}
