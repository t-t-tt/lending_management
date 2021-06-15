package taro.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * メッセージ出力クラス.<br>
 * 今回はログ出力と見立ててprintlnメソッドを使用します.
 */
@Aspect
@Component
public class MessageUtil {
	/**
	 * taroパッケージ内のメソッドを開始する際にメッセージを出力します.
	 * @param joinPoint
	 */
	@Before("execution(* taro.*.*.*(..))")
	public void beforeMessage(JoinPoint joinPoint) {
		System.out.println("----- " + joinPoint.getSignature() + "の処理を開始します. −−−−−");
	}

	/**
	 * taroパッケージ内のメソッドを終了する際にメッセージを出力します.
	 * @param joinPoint
	 */
	@After("execution(* taro.*.*.*(..))")
	public void afterMessage(JoinPoint joinPoint) {
		System.out.println("-----" + joinPoint.getSignature() + "の処理を終了します. -----");
	}

	/**
	 * taro.serviceパッケージ内のメソッドがreturnをする際にメッセージを出力します.
	 * @param joinPoint
	 */
	@AfterReturning("execution(* taro.service.*.*(..))")
	public void afterMessageReturn() {
		System.out.println("----- 処理を終了します. -----");
	}
}