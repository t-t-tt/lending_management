package taro.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import taro.entity.UserEntity;
import taro.form.UserForm;
import taro.service.LendService;
import taro.service.UserService;

/**
 * ユーザーコントローラクラスです.
 */
@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	LendService lendService;

	/**
	 * @return
	 */
	@GetMapping("/user")
	public String userToUserList() {
		// 次に表示する画面のパスを返却
		return "redirect:/user/list";
	}

	/**
	 * ユーザー一覧を表示します.
	 * @param model
	 * @return ユーザー一覧画面のパス
	 */
	/**
	 * ユーザー一覧画面表示
	 * @param model
	 * @return
	 */
	@GetMapping("/user/list")
	public String userList(Model model) {
		// DBに登録されているユーザーの一覧を取得
		List<UserEntity> userList = userService.findByIsDeletedFalse();

		// modelにイベントの一覧をセット
		model.addAttribute("userList", userList);

		// 次に表示する画面のパスを返却
		return "user/list";
	}

	/**
	 * ユーザー詳細を表示します.
	 * @param model
	 * @return ユーザー一覧画面のパス
	 */
	@GetMapping("/user/{id}")
	public String userDetail(@PathVariable("id") Integer id, Model model) {
		// DBに登録されているユーザーの一覧を取得
		UserEntity user = userService.findOneById(id);

		// modelにイベントの一覧をセット
		model.addAttribute("user", user);

		// 次に表示する画面のパスを返却
		return "user/detail";
	}

	/**
	 * ユーザー登録画面を表示
	 * @return
	 */
	@GetMapping("/user/regist")
	public String userRegist() {
		// 次に表示する画面のパスを返却
		return "/user/regist";
	}

	/**
	 * ユーザー情報の登録処理
	 * @param userForm
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/user/add")
	public String userAdd(@Validated @ModelAttribute("user") UserForm userForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(userForm);
			return "redirect:/user/regist";
		}
		// DBに登録
		userService.save(userForm);

		// 次に表示する画面のパスを返却
		return "redirect:/user/list";
	}

	/**
	 * ユーザー情報の更新処理
	 * @param id
	 * @param userForm
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/user/{id}/update")
	public String userUpdate(@PathVariable("id") Integer id, @Validated @ModelAttribute("user") UserForm userForm,
			BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			System.out.println(userForm);
			return "error.html";
		}

		// DBに登録
		userService.save(id, userForm);

		// 次に表示する画面のパス（htmlファイルの名称）を返却
		return "redirect:/user/list";
	}

	/**
	 * ユーザー情報の削除処理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/user/{id}/delete")
	public String userDelete(@PathVariable("id") Integer id) throws Exception {
		if (lendService.findByUserId(id).size() > 0)
			throw new Exception("現在貸出中の機器が存在します");
		userService.deleteById(id);
		// 次に表示する画面のパス（htmlファイルの名称）を返却
		return "redirect:/user/list";
	}

	/**
	 * ユーザー情報の検索
	 * @param id
	 * @return ユーザー一覧画面のパス
	 * @throws Exception
	 */
	@GetMapping("/user/search")
	public String userSearch(@RequestParam("searchText") String searchText, Model model) throws Exception {
		List<UserEntity> userList = userService.findByNameContainingAndIsDeletedFalse(searchText);

		// modelに機器の一覧、貸出PC・空きPCの数をセット
		model.addAttribute("userList", userList);
		model.addAttribute("searchText", searchText);

		// 次に表示する画面のパス（htmlファイルの名称）を返却
		return "/user/searchResult";
	}
}