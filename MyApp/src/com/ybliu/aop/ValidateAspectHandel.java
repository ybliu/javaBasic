package com.ybliu.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

 
/**
 * ��֤ע�⴦����
 * @author zhoufeng
 */
@Component
@Aspect
public class ValidateAspectHandel {

	/**
	 * ʹ��AOP��ʹ����ValidateGroup�ķ������д���У��
	 * @throws Throwable 
	 */
	@SuppressWarnings({ "finally", "rawtypes" })
	@Around("@annotation(com.zf.ann.ValidateGroup)")
	public Object validateAround(ProceedingJoinPoint joinPoint) throws Throwable  {
		boolean flag = false ;
		ValidateGroup an = null;
		Object[] args =  null ;
		Method method = null;
		Object target = null ;
		String methodName = null;
		try{
			methodName = joinPoint.getSignature().getName();
			target = joinPoint.getTarget();
			method = getMethodByClassAndName(target.getClass(), methodName);	//�õ����صķ���
			args = joinPoint.getArgs();		//�����Ĳ���	
			an = (ValidateGroup)getAnnotationByMethod(method ,ValidateGroup.class );
			flag = validateFiled(an.fileds() , args);
		}catch(Exception e){
			flag = false;
		}finally{
			if(flag){
				System.out.println("��֤ͨ��");
				return joinPoint.proceed();
			}else{	//����ʹ����Spring MVC �����з���ֵӦ��ΪStrng��ModelAndView ���������Struts2��ֱ�ӷ���һ��String��resutl������
				System.out.println("��֤δͨ��");
				Class returnType = method.getReturnType();	//�õ���������ֵ����
				if(returnType == String.class){	//�������ֵΪStirng
					return "/error.jsp";		//���ش���ҳ��
				}else if(returnType == ModelAndView.class){
					return new ModelAndView("/error.jsp");//���ش���ҳ��
				}else{	//��ʹ��Ajax��ʱ�� ���ܻ�����������
					return null ;
				}
			}
		}
	}

	/**
	 * ��֤�����Ƿ�Ϸ�
	 */
	public boolean validateFiled( ValidateFiled[] valiedatefiles , Object[] args) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		for (ValidateFiled validateFiled : valiedatefiles) {
			Object arg = null;
			if("".equals(validateFiled.filedName()) ){
				arg = args[validateFiled.index()];
			}else{
				arg = getFieldByObjectAndFileName(args[validateFiled.index()] ,
						validateFiled.filedName() );
			}

			if(validateFiled.notNull()){		//�жϲ����Ƿ�Ϊ��
				if(arg == null )
					return false;
			}else{		//����ò����ܹ�Ϊ�գ����ҵ�����Ϊ��ʱ���Ͳ����жϺ������ ��ֱ�ӷ���true
				if(arg == null )
					return true;
			}

			if(validateFiled.maxLen() > 0){		//�ж��ַ�����󳤶�
				if(((String)arg).length() > validateFiled.maxLen())
					return false;
			}

			if(validateFiled.minLen() > 0){		//�ж��ַ�����С����
				if(((String)arg).length() < validateFiled.minLen())
					return false;
			}

			if(validateFiled.maxVal() != -1){	//�ж���ֵ���ֵ
				if( (Integer)arg > validateFiled.maxVal()) 
					return false;
			}

			if(validateFiled.minVal() != -1){	//�ж���ֵ��Сֵ
				if((Integer)arg < validateFiled.minVal())
					return false;
			}

			if(!"".equals(validateFiled.regStr())){	//�ж�����
				if(arg instanceof String){
					if(!((String)arg).matches(validateFiled.regStr()))
						return false;
				}else{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * ���ݶ�����������õ� ����
	 */
	public Object getFieldByObjectAndFileName(Object targetObj , String fileName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String tmp[] = fileName.split("\\.");
		Object arg = targetObj ;
		for (int i = 0; i < tmp.length; i++) {
			Method methdo = arg.getClass().
					getMethod(getGetterNameByFiledName(tmp[i]));
			arg = methdo.invoke(arg);
		}
		return arg ;
	}

	/**
	 * ���������� �õ������Ե�getter������
	 */
	public String getGetterNameByFiledName(String fieldName){
		return "get" + fieldName.substring(0 ,1).toUpperCase() + fieldName.substring(1) ;
	}

	/**
	 * ����Ŀ�귽����ע������  �õ���Ŀ�귽����ָ��ע��
	 */
	public Annotation getAnnotationByMethod(Method method , Class annoClass){
		Annotation all[] = method.getAnnotations();
		for (Annotation annotation : all) {
			if (annotation.annotationType() == annoClass) {
				return annotation;
			}
		}
		return null;
	}

	/**
	 * ������ͷ������õ�����
	 */
	public Method getMethodByClassAndName(Class c , String methodName){
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if(method.getName().equals(methodName)){
				return method ;
			}
		}
		return null;
	}

}

