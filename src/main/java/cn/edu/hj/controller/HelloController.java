package cn.edu.hj.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping(value = "/hello")//��ʾҪ�������action��ʱ��Ҫ�������/hello·��
public class HelloController {
	
/* ���ղ���getParameter()��ʱ��:
 * ����ַ��/springmvc/hello.htm����û�д��ݲ���,��ô��idΪint�͵�ʱ��ᱨ��,��idΪInteger��ʱ��ֵΪnull
 * ����ַ��Ϊ/springmvc/hello.htm?id=10��ʱ��,action�������ֽ��շ�ʽ
 * 1��String hello(@RequestParam(value = "userid") int id),�����ѵ�ַ��������Ϊuserid��ֵ�������id,����õ�ַ���ϵĲ�����Ϊid,����ղ���
 * 2��String hello(@RequestParam int id),���������Ĭ�ϻ��id��Ϊ�����������н��ո�ֵ
 * 3��String hello(int id),���������Ҳ��Ĭ�ϰ�id��Ϊ�����������н��ո�ֵ
 * ע:������ǰ�����@RequestParamע��,����ַ������û�м��ϸ�ע��Ĳ���,����:id,��ô�ᱨ404����,�Ҳ�����·��
 */
	@RequestMapping(value = "/hello.htm")
	public String hello(int id){//getParameter()�ķ�ʽ
		System.out.println("hello action:"+id);
//		return "hello";
		return "redirect:/index.jsp";//�����ض���web-info������ļ�,������Ҫд�Ͼ��·��
	}
	
	//����ҳ�����ĵ�һ�ַ�ʽ,���β��з���һ��map
	@RequestMapping(value = "/hello1.htm")
	public String hello(int id,Map<String,Object> map){
		System.out.println("hello1 action:"+id);
		map.put("name", "huangjie");
		return "hello";
	}
	
	//����ҳ�����ĵڶ��ַ�ʽ,���β��з���һ��Model
	@RequestMapping(value = "/hello2.htm")
	public String hello2(int id,Model model){
		System.out.println("hello2 action:"+id);
		model.addAttribute("name", "huangjie");
		//���ֻ��ֵû�м�������,ʹ��Object��������Ϊkey,String-->string
		model.addAttribute("ok");
		return "hello";
	}

	//�õ�request,response,session��,ֻҪ�ڷ����β������������
	@RequestMapping(value = "/hello3.htm")
	public String hello3(HttpServletRequest request){
		String id = request.getParameter("id");
		System.out.println("hello3 action:"+id);
		return "hello";
	}
}
