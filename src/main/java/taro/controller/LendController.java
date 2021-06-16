package taro.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import taro.entity.EquipEntity;
import taro.entity.LendEntity;
import taro.entity.LendingManagement;
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

	@GetMapping("/")
	public String indexToLendList(Model model) {
		// 次に表示する画面のパスを返却
		return "redirect:/lend/list";
	}

	@GetMapping("/lend")
	public String lendToLendList(Model model) {
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
		List<LendingManagement> lendingManagementList = lendService.findAll();
		List<LendingManagement> expiredList = new ArrayList<LendingManagement>();

		int letedPcNum = 0;
		Date today = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
		for (LendingManagement lending : lendingManagementList) {
			if (lending.getEquip().getIsLent())
				letedPcNum++;

			if (lending.getLend() != null && lending.getLend().getLendEnd().compareTo(today) < 0) {
				expiredList.add(lending);
			}
		}
		System.out.println("after for");

		// modelにイベントの一覧をセット
		model.addAttribute("lendingManagementList", lendingManagementList);
		model.addAttribute("expiredList", expiredList);
		model.addAttribute("lentedPcNum", letedPcNum);
		model.addAttribute("notLentedPcNum", (lendingManagementList.size() - letedPcNum));

		// 次に表示する画面のパスを返却
		return "/lend/list";
	}

	/**
	 * 貸出返却画面を表示します.
	 * @param model
	 * @return 貸出返却画面のパス
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
	 * 貸出情報編集画面を表示します.
	 * @param model
	 * @return 貸出情報編集画面のパス
	 * @throws Exception
	 */
	@GetMapping("/lend/{id}")
	public String lendDetail(@PathVariable("id") Integer id, Model model) throws Exception {
		LendEntity lend = lendService.findOneById(id);
		UserEntity user = userService.findOneById(lend.getUserId());
		EquipEntity equip = equipService.findOneById(lend.getEquipId());

		if(lend == null || user == null || equip == null) throw new Exception("データが取得できませんでした。");

		System.out.println(lend);
		model.addAttribute("asset", equip.getAsset());
		model.addAttribute("employeeNumber", user.getEmployeeNumber());
		model.addAttribute("lend", lend);
		// 次に表示する画面のパスを返却
		return "lend/edit";
	}

	/**
	 * 貸出情報の貸出処理.
	 * @param model
	 * @return 貸出一覧画面のパス
	 * @throws Exception
	 */
	@PostMapping("/lend/edit")
	public String editLend(@Validated @ModelAttribute("lend") LendForm lendForm, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			System.out.println(lendForm);
			return "redirect:/lend/list";
		}
		System.out.println(lendForm);
		// DBに登録
		lendService.editLend(lendForm);

		// 次に表示する画面のパスを返却
		return "redirect:/lend/list";
	}

	/**
	 * 貸出情報の貸出処理.
	 * @param model
	 * @return 貸出一覧画面のパス
	 * @throws Exception
	 */
	@PostMapping("/lend/rent")
	public String rentPc(@Validated @ModelAttribute("lend") LendForm lendForm, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			System.out.println(lendForm);
			return "redirect:/lend/regist";
		}
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
	 * @throws Exception
	 */
	@PostMapping("/lend/dropoff")
	public String dropOffPc(@Validated @ModelAttribute("lend") LendForm lendForm, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			System.out.println(lendForm);
			return "error.html";
		}
		// DBに登録
		lendService.dropOffPc(lendForm);

		// 次に表示する画面のパス（htmlファイルの名称）を返却
		return "redirect:/lend/list";
	}
}