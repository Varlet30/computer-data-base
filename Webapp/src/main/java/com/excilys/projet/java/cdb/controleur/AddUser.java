package com.excilys.projet.java.cdb.controleur;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.projet.java.cdb.dto.UserDTO;
import com.excilys.projet.java.cdb.mapper.UserMapper;
import com.excilys.projet.java.cdb.service.ServiceUser;

@Controller
@RequestMapping(value = "/addUser")
public class AddUser {

	@Autowired
    private final ServiceUser serviceUser;

	private static final String SUCESSMANAGE = "successMessage";

    public AddUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping
    public ModelAndView register() {
        return new ModelAndView("addUser");
    }

    @PostMapping
    @Transactional
    public ModelAndView register(UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");

        serviceUser.addUser(UserMapper.fromUserDTOtoUser(userDTO));

        setMessage("user Added", SUCESSMANAGE, modelAndView);

        return modelAndView;
    }

	public void setMessage(String errorMessage, String messageTitle, ModelAndView modelAndView) {
		if (( errorMessage != null )) {
			modelAndView.addObject(messageTitle, errorMessage);
		}
	}
}