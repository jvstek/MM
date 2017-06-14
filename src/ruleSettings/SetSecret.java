package ruleSettings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SetSecret {

	private List<Integer> SecretCodeList = new ArrayList<Integer>();
	private Random rand = new Random();
	private Integer until = 0;
	private Integer secret = 0;
	/**
	 * Create random secret numbers  (that represent colors) 
	 * 
	 * @param AmountOfSecretRow
	 * @param MaximumSameSecretsAllowed
	 * @param AmountOfColors
	 * @return
	 */
	public List<Integer> SetSecretCode(Integer AmountOfSecretRow, Integer MaximumSameSecretsAllowed,
			Integer AmountOfColors) {
		while (until < AmountOfSecretRow) {
			secret = rand.nextInt(AmountOfColors);
			if (AddSecretAllowed(MaximumSameSecretsAllowed, SecretCodeList, secret)) {

				SecretCodeList.add(secret);
				until++;
			}
		}
//		for (Integer secies : SecretCodeList) {
//			System.out.print(secies);
//		} 
//		System.out.println();
		// show this code so the result is printed in the console
		return SecretCodeList;
	}
	/**
	 * Tell if the secret is allowed in the list. provided by the given parameters 
	 * @param MaximumSameAllowed
	 * @param GivenSecretCodeList
	 * @param SecretToAdd
	 * @return
	 */
	private boolean AddSecretAllowed(Integer MaximumSameAllowed, List<Integer> GivenSecretCodeList,
			Integer SecretToAdd) {
		if (GivenSecretCodeList.size() < MaximumSameAllowed) {
			return true;
		}
		Set<Integer> uniqueSet = new HashSet<Integer>(GivenSecretCodeList);
		for (Integer temp : uniqueSet) {
			// check if the value to add is the same as the current value
			if (temp == SecretToAdd) {
				// if the value is the same check if the amount doesn't exceed
				// the max amount allowed
				if ((Collections.frequency(GivenSecretCodeList, temp) + 1) > MaximumSameAllowed) {
					return false; // if it exceeds return false
				}
			}
		}
		return true; // return true;
	}
}
