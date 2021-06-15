package taro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import taro.entity.EquipEntity;
import taro.entity.LendEntity;
import taro.entity.UserEntity;
import taro.form.LendForm;
import taro.service.EquipService;
import taro.service.LendService;
import taro.service.UserService;

/**
 * 貸出コントローラクラスです.
 */
@Controller
public class LendController {

	@Autowired
	LendService lendService;

	@Autowired
	EquipService equipService;

	@Autowired
	UserService userService;

	@GetMapping("/lend")
	public String index(Model model) {
		// 次に表示する画面のパスを返却
		return "redirect:/lend/list";
	}

	/**
	 * 貸出一覧を表示します.
	 * @param model
	 * @return 貸出一覧画面のパス
	 */
	@GetMapping("/lend/list")
	public String lendList(Model model) {
		// DBに登録されている貸出の一覧を取得
		List<EquipEntity> equipList = equipService.findByIsDeletedFalse();
		List<UserEntity> userList = userService.findByIsDeletedFalse();
		List<LendEntity> lendList = lendService.findAll();
		System.out.println(lendList.size()+"貸出一覧");

		int letedPcNum = 0;
		for (EquipEntity equip:equipList) {
			if(equip.getIsLent()) letedPcNum++;
		}
		// modelにイベントの一覧をセット
		model.addAttribute("equipList", equipList);
		model.addAttribute("userList", userList);
		model.addAttribute("lendList", lendList);
		model.addAttribute("lentedPcNum", letedPcNum);
		model.addAttribute("notLentedPcNum", (lendList.size() - letedPcNum));

		// 次に表示する画面のパスを返却
		return "lend/list";
	}

	/**
	 * 貸出詳細を表示します.
	 * @param model
	 * @return 貸出詳細画面のパス
	 */
	@GetMapping("/lend/management")
	public String lendDetail(Model model) {
		List<EquipEntity> equipList = equipService.findByIsDeletedFalse();
		List<UserEntity> userList = userService.findByIsDeletedFalse();

		model.addAttribute("equipList", equipList);
		model.addAttribute("userList", userList);
		// 次に表示する画面のパスを返却
		return "lend/management";
	}

	/**
	 * 貸出情報の貸出処理.
	 * @param model
	 * @return 貸出一覧画面のパス
	 */
	@PostMapping("/lend/rent")
	public String rentPc(@Validated @ModelAttribute("lend") LendForm lendForm, BindingResult bindingResult) {
//		if(bindingResult.hasErrors()) {
//			System.out.println(lendForm);
//			return "redirect:/lend/regist";
//		}
		System.out.println(lendForm);
		// DBに登録
		lendService.rentPc(lendForm);

		// 次に表示する画面のパスを返却
		return "redirect:/lend/list";
	}

	/**
	 * 貸出情報の返却処理
	 * @param model
	 * @return 貸出一覧画面のパス
	 */
	@PostMapping("/lend/dropoff")
	public String dropOffPc(@Validated @ModelAttribute("lend") LendForm lendForm, BindingResult bindingResult) {
//		if(bindingResult.hasErrors()) {
//			System.out.println(lendForm);
//			return "error.html";
//		}
		// DBに登録
		lendService.dropOffPc(lendForm);

		// 次に表示する画面のパス（htmlファイルの名称）を返却
		return "redirect:/lend/list";
	}
}