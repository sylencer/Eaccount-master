package com.eaccount.dao;

import com.eaccount.domain.Order_detail;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by spzn on 16-3-4.
 */
public class OrderDetailDAO implements IOrderDetailDAO{
    @Override
    public List<Order_detail> SellerGetOrderDetailInfoByOrderId(Order_detail order_detail) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        dbAccess.GetLog();
        List<Order_detail> list = new ArrayList<Order_detail>();
        try {
            sqlSession = dbAccess.getSqlSession();
            list = sqlSession.selectList("Order_detail.SelectOrderDetailInfoByOrderId", order_detail);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return list;
    }

    @Override
    public List<Order_detail> GetOrderDetailInfoByMatterOrderId(Order_detail order_detail) {
         DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        dbAccess.GetLog();
        List<Order_detail> list = new ArrayList<Order_detail>();
        try {
            sqlSession = dbAccess.getSqlSession();
            list = sqlSession.selectList("Order_detail.SelectOrderDetailInfoByMatterOrderId", order_detail);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return list;
    }

    @Override
    public boolean UpdateQuantity(Order_detail order_detail) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        dbAccess.GetLog();
        boolean flag = false;
        int cnt = 0;
        try {
            sqlSession = dbAccess.getSqlSession();
            if ("1".equals(order_detail.getType())) {
                cnt = sqlSession.update("Order_detail.SellerUpdateQuantity", order_detail);
            } else {
                cnt = sqlSession.update("Order_detail.BuyerUpdateQuantity", order_detail);
            }
            sqlSession.update("Order.UpdateTotalPriceSeller", order_detail.getOrder_id());
            sqlSession.update("Order.UpdateTotalPriceBuyer", order_detail.getOrder_id());
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        if (cnt != 0) flag = true;
        return flag;
    }

    @Override
    public boolean BuyerSetQuantity(Order_detail order_detail) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        dbAccess.GetLog();
        boolean flag = false;
        int cnt = 0;
        try {
            sqlSession = dbAccess.getSqlSession();
            cnt = sqlSession.update("Order_detail.BuyerSetQuantity", order_detail);
            sqlSession.update("Order.UpdateTotalPriceSeller", order_detail.getOrder_id());
            sqlSession.update("Order.UpdateTotalPriceBuyer", order_detail.getOrder_id());
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        if (cnt != 0) flag = true;
        return flag;
    }

    @Override
    public String GetOrderIdBuyOrderDetailId(Order_detail order_detail) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        dbAccess.GetLog();
        List<String> list = new ArrayList<>();
        try {
            sqlSession = dbAccess.getSqlSession();
            list = sqlSession.selectList("Order_detail.SelectOrderIdByOrderDetailId", order_detail);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return list.get(0);
    }

    @Override
    public boolean InsertOrderDetails(Order_detail order_detail) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        dbAccess.GetLog();
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.selectList("Order_detail.InsertOrderDetails", order_detail);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return true;
    }

    @Override
    public boolean CountMatterOrder(String order_id) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        dbAccess.GetLog();
        List<Integer> list = new ArrayList<>();
        int num = 0;
        try {
            sqlSession = dbAccess.getSqlSession();
            list = sqlSession.selectList("Order_detail.CountMatterOrder", order_id);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        if (list == null || list.get(0) == 0) return true;
        return false;
    }
}
