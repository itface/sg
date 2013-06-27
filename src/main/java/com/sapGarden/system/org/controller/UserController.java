package com.sapGarden.system.org.controller;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sapGarden.system.org.model.NewUser;
import com.sapGarden.system.org.service.UserService;

@Controller
@RequestMapping(value="/system/user")
public class UserController {

	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping
	public ModelAndView index(){
		return new ModelAndView("/sys_user_manage");
	}
	@RequestMapping(value=("/data"),method = RequestMethod.GET)
	public @ResponseBody Object showData(){
		JSONObject jsonObject = userService.findAllJson();
		return jsonObject==null?"{}":jsonObject;
	}
	@RequestMapping(value=("/data"),method = RequestMethod.POST)
	public @ResponseBody void _new(NewUser newUser){
		userService.addOneByNewUser(newUser);
	}
	@RequestMapping(value=("/data/{id}"),method = RequestMethod.PUT)
	public @ResponseBody void update(NewUser newUser){
		userService.updateOne(newUser);
	}
	@RequestMapping(value=("/data/{id}"),method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable long id){
		userService.deleteOne(id);
	}
	@RequestMapping(value=("/getUserSelect"),method = RequestMethod.GET)
	public @ResponseBody Object getUserSelect(){
		return userService.findUserSelect();
	}
}
