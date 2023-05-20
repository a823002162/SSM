package com.atguigu.mapper;

import com.atguigu.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    //1.单个简单参数
    Employee getEmployeeById(Integer ID);

    //2.实体类型参数
    void addEmployee(Employee employee);

    //3.1零散的简单类型参数方式1 arg0,arg1... param1,param2...
    void addEmployeeByArgs(String empName,Double empSalary);

    //3.2零散的简单类型参数方式2 @Param
    void addEmployeeByParam(@Param("ename")String ename, @Param("esalary") Double esalary);

    //4.Map类型参数
    void addEmployeeByMap(Map<String,Object> map);
    void editEmployeeByMap(Map<String,Object> map);

    //返回Map类型
    Map<String,Object> getEmployeeAndSalary();

    //返回list集合
    List<Map<String,Object>> selectAll();

    //测试resultMap自定义标签对应关系
    List<Employee> selectAllResultMap();

    //测试动态sql中where if标签
    List<Employee> selectEmployeeByCondition(Employee employee);

    //测试set标签
    void updateEmployeeDynamic(Employee employee);
    //测试trim标签
    List<Employee> selectEmployeeByConditionByTrim(Employee employee);
    //测试choose when otherwise标签
    List<Employee> selsectEmployeeByConditionByChoose(Employee employee);

    //foreach之批量添加
    void addEmployeeByBatch(@Param("empList") List<Employee> empList);
    //foreach之批量更新
    void updateEmployeeByBatch(@Param("empList") List<Employee> empList);

    //foreach之批量查询
    List<Employee> selectEmployeeByBatch(@Param("empList") List<Integer> empList);

    //测试缓存的查询语句
    List<Employee> selectAllEmpWithCache();
}
