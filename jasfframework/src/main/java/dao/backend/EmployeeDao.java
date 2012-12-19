package dao.backend;

import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import services.service;

public class EmployeeDao {
		
	public static PersistenceManagerFactory pmf =
            JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	     public static PersistenceManager pm = pmf.getPersistenceManager();

	public static void main (String args[])
	{
		String name;
		String designation;
		double salary;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter employee Name");
		name = sc.nextLine();
		System.out.println("Enter employee Designation");
		designation = sc.nextLine();
		System.out.println("Enter employee salary");
		salary = sc.nextLong();
		Employee emp1 = new Employee(name,designation,salary);
//        add(emp1);
//		System.out.println("enter empId for fetching corresponding deatils");
////		int empId = sc.nextInt();
		search(33);
	}
	
	public static Employee add(Employee emp)
	{
		Employee e;
		System.out.println("Comes here from post method");
		
		Transaction tx=pm.currentTransaction();
		try
		{
		    tx.begin();
//		    Employee emp1 = new Employee("Abc",30000,"qwerty");
		    
		     e= pm.detachCopy(pm.makePersistent(emp));
				System.out.println(e.getEmployeeId());
				System.out.println(e.getName());
				System.out.println(e.getDesignation());
				System.out.println(e.getSalary());
		    tx.commit();
//		    return e;
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
//		    pm.close();
		  
		}
		  return e;
	}
	
	public static Employee search(int b)
	{
		Employee p=null;
		Transaction tx=pm.currentTransaction();
		System.out.println("Comes here from get method");
		try
		{
			tx.begin();

		    Query q = pm.newQuery(Employee.class, "employeeId=="+b);
		    List<Employee> em = (List<Employee>)q.execute();
		   // java.util.List<E>
//		    List em1 = (List)q.execute();
		    Iterator<Employee> iter = em.iterator();
		    while (iter.hasNext())
		    {
		         p = iter.next();
		        System.out.print(p.getName()+" \t ");
		        System.out.print(p.getDesignation()+" \t");
		        System.out.println(p.getSalary());
		        
//				service s = new service();
////				Employee emp1 = s.getTrackInJSON();
		    }

		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
		}
		  return p;
	
		
		
	}
	
	public static Employee update(Employee emp)
	{
		Employee e;
		System.out.println("Comes here from put method");
		Employee employee=null;
		
		Transaction tx=pm.currentTransaction();
		try
		{
		    tx.begin();
		    Query q = pm.newQuery(Employee.class);
		    List<Employee> em=(List<Employee>) q.execute();
		    Iterator<Employee> iter=em.iterator();
		    while (iter.hasNext()) {
				employee = (Employee) iter.next();
				if(employee.getEmployeeId()==emp.getEmployeeId()){
					employee.setDesignation(emp.getDesignation());
					employee.setName(emp.getName());
					employee.setSalary(emp.getSalary());
					break;
				}
				
			}
		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
//		    pm.close();
		  
		}
		  return employee;
	}
	
	public static Employee delete(int emp)
	{
		Employee e=null;
		System.out.println("Comes here from delete method");
		Employee employee=null;
		
		Transaction tx=pm.currentTransaction();
		try
		{
		    tx.begin();
		    Query q = pm.newQuery(Employee.class,"employeeId=="+emp);
		   List<Employee> em=(List<Employee>) q.execute();
		   Iterator<Employee> iter=em.iterator();
		   if(iter.hasNext())
		   employee=iter.next();
		   System.out.println("Error 1");
		   e=(Employee)employee.clone();
		   System.out.println(e);
//		   System.out.println("Error 2");
		   pm.deletePersistentAll(employee);
//		   System.out.println("Error 3");
		    
		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
//		    pm.close();
		  
		}
		  return e;
	}
	
}
