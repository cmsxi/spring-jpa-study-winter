package jpastudy.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
//        Model: Spring UI에서는 Model에 어떤 데이터를 실어서 컨트롤러에서 뷰로 데이터를 넘길 수 있다.
        model.addAttribute("data", "hello");
        return "hello";
    }

}
