package www.wuluyang.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

//数据源1
@MapperScan(basePackages="www.wuluyang.metting",sqlSessionFactoryRef="mettingSqlSessionFactory")//@MapperScan扫描包范围
public class metting {
	@Bean(name="mettingDataSource")
	@ConfigurationProperties(prefix="spring.datasource.metting")
	public DataSource testDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	public SqlSessionFactory mettingSqlSession(@Qualifier("mettingDataSource") DataSource dataSource) throws Exception{
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}
	
	@Bean(name="mettingTransactionManager")
	public DataSourceTransactionManager mettingTransactionManager(@Qualifier("mettingDataSource") DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}
	@Bean(name="mettingSqlSessionTemplate")
	public SqlSessionTemplate mettingSqlSessionTemplate
	(@Qualifier("mettingSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory);
		
	}
}
