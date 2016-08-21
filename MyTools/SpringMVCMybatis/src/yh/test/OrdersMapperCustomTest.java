package yh.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import yh.dao.OrdersMapperCustom;
import yh.model.Orders;
import yh.model.OrdersCustom;
import yh.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:config/spring-mvc.xml", "classpath:config/db.xml"})
public class OrdersMapperCustomTest {
	
	@Autowired 
	private SqlSessionTemplate sqlSession;

	@Test
	public void testFindOrdersUser() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<OrdersCustom> ordersCustomList = ordersMapperCustom.findOrdersUser();
		for(OrdersCustom ordersCustom : ordersCustomList){
			System.out.println(ordersCustom);
		}
	}
	
	@Test
	public void testFindOrdersUserResultMap() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> ordersList = ordersMapperCustom.findOrdersUserResultMap();
		for(Orders orders : ordersList){
			System.out.println(orders);
		}
	}
	
	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> ordersList = ordersMapperCustom.findOrdersAndOrderDetailResultMap();
		for(Orders orders : ordersList){
			System.out.println(orders);
		}
	}
	
	@Test
	public void testFindUserAndItemsResultMap() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<User> userList = ordersMapperCustom.findUserAndItemsResultMap();
		for(User user : userList){
			System.out.println(user);
		}
	}
	
	@Test
	public void testFindOrdersUserLazyLoading() throws Exception{
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> ordersList = ordersMapperCustom.findOrdersUserLazyLoading();
		for(Orders orders : ordersList){
			System.out.println(orders);
		}
	}
}
