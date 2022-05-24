package tacos.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
	
	private RestTemplate rest = new RestTemplate();

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		log.info("Order submitted: " + order);
		rest.postForObject("http://localhost:8080/order", order, Order.class);
		return "redirect:/";
	}
	
//	@PostMapping
//	public String processDesign(@RequestParam("ingredients") String ingredientIds, @RequestParam("name") String name) {
//		List<Ingredient> ingredients = new ArrayList<Ingredient>();
//		for (String ingredientId : ingredientIds.split(",")) {
//			Ingredient ingredient = rest.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class,
//					ingredientId);
//			ingredients.add(ingredient);
//		}
//		Taco taco = new Taco();
//		taco.setName(name);
//		taco.setIngredients(ingredients);
//		System.out.println(taco);
//		rest.postForObject("http://localhost:8080/design", taco, Taco.class);
//		return "redirect:/orders/current";
//	}
}
