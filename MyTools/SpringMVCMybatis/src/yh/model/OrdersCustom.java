package yh.model;
//通过此类映射订单和用户查询的结果，让此类继承包括字段较多的model类
public class OrdersCustom {
		//添加用户属性
		private String username;
		private String sex;
		private String address;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		@Override
		public String toString() {
			return "OrdersCustom [address=" + address + ", sex=" + sex
					+ ", username=" + username + "]";
		}

		
}
