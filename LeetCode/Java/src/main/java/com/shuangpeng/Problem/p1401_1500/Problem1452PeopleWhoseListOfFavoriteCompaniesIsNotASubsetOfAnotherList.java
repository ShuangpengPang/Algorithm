package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1452PeopleWhoseListOfFavoriteCompaniesIsNotASubsetOfAnotherList（收藏清单）
 * @date 2025/4/22 17:11
 */
public class Problem1452PeopleWhoseListOfFavoriteCompaniesIsNotASubsetOfAnotherList {

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        int n = favoriteCompanies.size();
        List<Set<String>> sets = new ArrayList<>(n);
        for (List<String> f : favoriteCompanies) {
            Set<String> set = new HashSet<>();
            for (String s : f) {
                set.add(s);
            }
            sets.add(set);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Set<String> set1 = sets.get(i);
            boolean isNoSubSet = true;
            for (int j = 0; j < n && isNoSubSet; j++) {
                if (i != j) {
                    Set<String> set2 = sets.get(j);
                    boolean isSubset = true;
                    for (String s : set1) {
                        if (!set2.contains(s)) {
                            isSubset = false;
                            break;
                        }
                    }
                    isNoSubSet = !isSubset;
                }
            }
            if (isNoSubSet) {
                ans.add(i);
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1452PeopleWhoseListOfFavoriteCompaniesIsNotASubsetOfAnotherList a = new Problem1452PeopleWhoseListOfFavoriteCompaniesIsNotASubsetOfAnotherList();
//        String[][] array = {{"nxaqhyoprhlhvhyojanr","ovqdyfqmlpxapbjwtssm","qmsbphxzmnvflrwyvxlc","udfuxjdxkxwqnqvgjjsp","yawoixzhsdkaaauramvg","zycidpyopumzgdpamnty"},{"nxaqhyoprhlhvhyojanr","ovqdyfqmlpxapbjwtssm","udfuxjdxkxwqnqvgjjsp","yawoixzhsdkaaauramvg","zycidpyopumzgdpamnty"},{"ovqdyfqmlpxapbjwtssm","qmsbphxzmnvflrwyvxlc","udfuxjdxkxwqnqvgjjsp","yawoixzhsdkaaauramvg","zycidpyopumzgdpamnty"}};
//        List<List<String>> lists = new ArrayList<>();
//        for (String[] arr : array) {
//            List<String> list = new ArrayList<>();
//            for (String s : arr) {
//                list.add(s);
//            }
//            lists.add(list);
//        }
//        a.peopleIndexes(lists);
//    }
}
