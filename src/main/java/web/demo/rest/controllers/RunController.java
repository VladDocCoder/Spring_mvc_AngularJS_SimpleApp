package web.demo.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RunController {

	  @RequestMapping(method = RequestMethod.GET)
	    public String getIndexPage() {
	        return "WelcomeFileList";
	    }

}