package cn.edu.hj.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.edu.hj.bean.User;

@Controller
@RequestMapping(value = "/user")
//д�����ע��Ļ���ô��Model�н������keyΪloginUser��ʱ���Ĭ����ӵ�session��
@SessionAttributes(value = "loginUser")
public class UserController {
	
	Map<String,User> users = new LinkedHashMap<String,User>();
	public UserController(){//�޲���Ĺ��췽����Ĭ�ϱ�����һ��
		System.out.println("��ʼ��....");
		users.put("ldh",new User("ldh", "123", "ldh@qq.com"));
		users.put("gfc",new User("gfc", "123", "gfc@qq.com"));
		users.put("zxy",new User("zxy", "123", "zxy@qq.com"));
		users.put("lm ",new User("lm ", "123", " lm@qq.com"));
	}
	/**
	 * ��ʼ��user�б���Ϣ
	 * @param model
	 * @param user
	 */
	public void init(Model model,User user){
		if(user != null){
			users.put(user.getUsername(), user);
		}
		model.addAttribute("users", users);
	}
	
	/**
	 * ��ת��listҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list.htm")
	public String list(Model model){
		init(model, null);
		return "user/userlist";
	}
	
	/**
	 * ��ת�����ҳ��
	 * @return
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)//ʹ��get������ʱ��
	public String add(Model model){
		//��Ϊ��ҳ�����и�modelAttribute="user" user��������Ҫ����һ���յĹ�ȥ�����򱨴�
		model.addAttribute(new User());//Ĭ�ϵļ�Ϊֵ������,Ϊuser
		return "user/adduser";
	}
	
	/**
	 * ִ����Ӳ���,����ת��listҳ��
	 * @param user ���Ҫ��֤ǰ����Ҫ����@Validע��
	 * @param binding ���������֤��ʱ�����Ϣ�������а�,���Եõ���֤�Ľ����Ϣ
	 * binding�Ĳ������Ҫ����Ҫ��֤���Ǹ�����֮��
	 * @param model 
	 * @return
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.POST)//ʹ��post������ʱ��
	public String add(@Valid User user,BindingResult binding,Model model){
		if(binding.hasErrors()){
			return "user/adduser";
		}
		init(model, user);
		return "redirect:/user/list.htm";
	}
	
	/**
	 * ���username�õ�user����
	 * ·��Ϊ��/springmvc/user/ldh.htm
	 * @param usernameǰ�����@PathVariable��ʾ·������,���@RequestMapping��{username}���ж�Ӧ
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{username}.htm",method = RequestMethod.GET)
	public String show(@PathVariable String username,Model model){
		User user = users.get(username);
		model.addAttribute("user", user);
		return "user/show";
	}
	
	/**
	 * ��ɾ�����,url����REST���
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/delete/{username}.htm",method = RequestMethod.GET)
	public String delete(@PathVariable String username){
		users.remove(username);
		return "redirect:/user/list.htm";
	}
	
	/**
	 * ���username�õ�userת������jspҳ��
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/update/{username}.htm",method = RequestMethod.GET)
	public String update(@PathVariable String username,Model model){
		User user = users.get(username);
		model.addAttribute("user", user);
		return "user/adduser";
	}
	/**
	 * �����²���,url����REST���
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update/{username}.htm",method = RequestMethod.POST)
	public String update(@PathVariable String username,@Valid User user,BindingResult br){
		if(br.hasErrors()){
			return "/user/adduser";
		}
		users.put(user.getUsername(), user);
		return "redirect:/user/list.htm";
	}
	
	/**
	 * params = "json"�൱���ڷ���������д��@RequestParam String json
	 * ��ô���ʸ���ͼ�Ļ�������/springmvc/user/{username}.htm?json=..
	 * Ŀǰtomcat��֧�ִ����͵Ľ������������Խ��������Ƿ��ز���
	 * @param username
	 * @param model
	 * @return
	 */
	@ResponseBody//���ϸ�ע��,���Է��ض��������String����
	@RequestMapping(value = "/{username}.htm",params = "json")
	public User showJson(@PathVariable String username,Model model){
		System.out.println("username:"+username);
		return users.get(username);
	}
	
	/**
	 * ת����¼����
	 * @return
	 */
	@RequestMapping(value = "/login.htm",method = RequestMethod.GET)
	public String login(){
		return "/user/login";
	}
	
	/**
	 * �ڵ�¼������е�¼
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login.htm",method = RequestMethod.POST)
	public String login(String username,String password,Model model){
		if(!users.containsKey(username)){
			throw new RuntimeException("�û������!");
		}
		if(!password.equals(users.get(username).getPassword())){
			throw new RuntimeException("���벻��ȷ");
		}
		//�����session��,��Ϊǰ���Ѿ�����@SessionAttributes(value = "loginUser")ע��
		model.addAttribute("loginUser", users.get(username));
		return "redirect:/user/list.htm";
	}
	
	/**
	 * �����쳣�Ĳ���,�����Ǹ�action���׳���RuntimeException��������������в���
	 * @param ex
	 * @param req
	 * @return
	 */
	@ExceptionHandler(value = {RuntimeException.class})
	public String handlerException(Exception ex,HttpServletRequest req){
		req.setAttribute("ex", ex);//���쳣����request������
		return "error";//ת��errorҳ��
	}
	
	/**
	 * ʹ���ض��������д��ݶ���
	 * @param model
	 * @param ra
	 * @return
	 */
	@RequestMapping(value = "/redir.htm")
	public String redir(Model model,RedirectAttributes ra){
//		model.addAttribute("movie", "������");//ʹ�����ַ�ʽ���ض����Ǵ��ݲ��˵�
		ra.addFlashAttribute("movie", "������");//ʹ�����ֿ���
		return "redirect:/user/list.htm";
	}
	
	/**
	 * ��ת���ϴ�ҳ��
	 * @param photo
	 * @return
	 */
	@RequestMapping(value = "upload.htm",method = RequestMethod.GET)
	public String uploadPhoto(){
		return "user/upload";
	}
	
	/**
	 * ���е����ļ��ϴ�
	 * @param photo �����������Ҫ��?���ϴ���name�������һ��,�����ϴ����ɹ�
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "upload.htm",method = RequestMethod.POST)
	public String uploadPhoto(MultipartFile photo,Model model,HttpServletRequest req){
		System.out.println(photo.getContentType());//�ļ�������,image/jpeg
		System.out.println(photo.getName());//�ϴ��ı?���
		System.out.println(photo.getOriginalFilename());//�ϴ����ļ���
		String realpath = req.getSession().getServletContext().getRealPath("/upload/");
		System.out.println(realpath);//������webappĿ¼��
		try {
			FileUtils.copyInputStreamToFile(photo.getInputStream(), new File(realpath + "/" +photo.getOriginalFilename()));
		} catch (IOException e) {e.printStackTrace();}
		model.addAttribute("message", "�ϴ��ɹ�");
		return "user/upload";
	}
	
	/**
	 * ���ж���ļ��ϴ�
	 * @param photos �����������Ҫ��?���ϴ���name�������һ��,�����ϴ����ɹ�
	 * �������ϴ���ʱ����Ҫ�������ע��@RequestParam
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "uploads.htm",method = RequestMethod.POST)
	public String uploadPhoto(@RequestParam(required = false)MultipartFile[] photos,Model model,HttpServletRequest req){
		String realpath = req.getSession().getServletContext().getRealPath("/upload/");
		try {
			for(MultipartFile photo : photos){
				if(photo.isEmpty())continue;//���ܻ�������ϴ��?ȴ��һ������������ϴ�����������ľ�Ϊ����Ҫ�ж�
				FileUtils.copyInputStreamToFile(photo.getInputStream(), new File(realpath + "/" +photo.getOriginalFilename()));
			}
		} catch (IOException e) {e.printStackTrace();}
		model.addAttribute("message", "�ϴ��ɹ�");
		return "user/upload";
	}
}
