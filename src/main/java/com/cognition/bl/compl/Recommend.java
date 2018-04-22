/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cognition.bl.compl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Recommend {

	/**
	 *  ��һ��  �����û�ƫ�����ݣ��ó������û�ƫ�ö�, ��ѡ�� Ƥ��ѷ���ϵ��  ���� ŷ����þ��룬����û����ƶ�ѡ�񼯺�
	 *  �ڶ���  
	 * @param args
	 */
    public static void main(String[] args) {
        Map<String, Map<String, Integer>> userPerfMap = new HashMap<String, Map<String, Integer>>();
        Map<String, Integer> pref1 = new HashMap<String, Integer>();
        pref1.put("A", 3);
        pref1.put("B", 4);
        pref1.put("C", 3);
        pref1.put("D", 5);
        pref1.put("E", 1);
        pref1.put("F", 4);
        userPerfMap.put("p1", pref1);
        Map<String, Integer> pref2 = new HashMap<String, Integer>();
        pref2.put("A", 2);
        pref2.put("B", 4);
        pref2.put("C", 4);
        pref2.put("D", 5);
        pref2.put("E", 3);
        pref2.put("F", 2);
        userPerfMap.put("p2", pref2);
        Map<String, Integer> pref3 = new HashMap<String, Integer>();
        pref3.put("A", 3);
        pref3.put("B", 5);
        pref3.put("C", 4);
        pref3.put("D", 5);
        pref3.put("E", 2);
        pref3.put("F", 1);
        userPerfMap.put("p3", pref3);
        Map<String, Integer> pref4 = new HashMap<String, Integer>();
        pref4.put("A", 2);
        pref4.put("B", 2);
        pref4.put("C", 3);
        pref4.put("D", 4);
        pref4.put("E", 3);
        pref4.put("F", 2);
        userPerfMap.put("p4", pref4);
        Map<String, Integer> pref5 = new HashMap<String, Integer>();
        pref5.put("A", 4);
        pref5.put("B", 4);
        pref5.put("C", 4);
        pref5.put("D", 5);
        pref5.put("E", 1);
        pref5.put("F", 0);
        userPerfMap.put("p5", pref5);
        // �����û� ƫ�ö� Ƥ��ѷ  ����
        Map<String, Double> simUserSimMap = new HashMap<String, Double>();
        // �����û� ƫ�ö� ŷ�����  ����
        Map<String, Double> simUserEuclMap = new HashMap<String, Double>();
        String output1 = ""
        		+ ""
        		+ "���ϵ��:", output2 = "ŷ����þ���:";
        for (Entry<String, Map<String, Integer>> userPerfEn : userPerfMap.entrySet()) {
            String userName = userPerfEn.getKey();
            if (!"p5".equals(userName)) {
                double sim = getUserSimilar(pref5, userPerfEn.getValue());
                double distance = getEuclidDistance(pref5, userPerfEn.getValue());
                output1 += "p5��" + userName + "֮������ϵ��:" + sim + ",";
                output2 += "p5��" + userName + "֮��ľ���:" + distance + ",";
                simUserSimMap.put(userName, sim);
                simUserEuclMap.put(userName, distance);
            }
        }
        System.out.println(output1);
        System.out.println(output2);
        Map<String, Map<String, Integer>> simUserObjMap = new HashMap<String, Map<String, Integer>>();
        Map<String, Integer> pobjMap1 = new HashMap<String, Integer>();
        pobjMap1.put("һҹ��ϲ", 3);
        pobjMap1.put("��̫ƽ��", 4);
        pobjMap1.put("���ν��", 3);
        simUserObjMap.put("p1", pobjMap1);
        Map<String, Integer> pobjMap2 = new HashMap<String, Integer>();
        pobjMap2.put("һҹ��ϲ", 5);
        pobjMap2.put("��̫ƽ��", 1);
        pobjMap2.put("���ν��", 2);
        simUserObjMap.put("p2", pobjMap2);
        Map<String, Integer> pobjMap3 = new HashMap<String, Integer>();
        pobjMap3.put("һҹ��ϲ", 2);
        pobjMap3.put("��̫ƽ��", 5);
        pobjMap3.put("���ν��", 5);
        simUserObjMap.put("p3", pobjMap3);
        System.out.println("Ƥ��ѷϵ���Ƽ�:" + getRecommendPearson(simUserObjMap, simUserSimMap));
        System.out.println("ŷ�����ϵ���Ƽ�:" + getRecommendEuclid(simUserObjMap, simUserEuclMap));
    }
    
    /**
    *
    * @Description����Ƥ��ѷϵ���õ� �Ŷ����� ���һ��
    * @author qinshijiang
    * @Date 2013-9-4
    */
   public static String getRecommendPearson(Map<String, Map<String, Integer>> simUserObjMap, Map<String, Double> simUserSimMap) {
	   List<Entry<String, Double>> enList = getRecommentList(simUserObjMap, simUserSimMap);
       return enList.get(enList.size() - 1).getKey();
   }
   
   public static String getRecommendEuclid(Map<String, Map<String, Integer>> simUserObjMap, Map<String, Double> simUserSimMap) {
	   List<Entry<String, Double>> enList = getRecommentList(simUserObjMap, simUserSimMap);
       return enList.size() > 0 ?enList.get(0).getKey():"";
   }
   
   
   /**
    *  ����������б�
    * @param simUserObjMap
    * @param simUserSimMap
    * @return
    */
   private static List<Entry<String, Double>> getRecommentList(Map<String, Map<String, Integer>> simUserObjMap, Map<String, Double> simUserSimMap){
	     Map<String, Double> objScoreMap = new HashMap<String, Double>();
	       for (Entry<String, Map<String, Integer>> simUserEn : simUserObjMap.entrySet()) {
	           String user = simUserEn.getKey();
	           double sim = simUserSimMap.get(user);
	           for (Entry<String, Integer> simObjEn : simUserEn.getValue().entrySet()) {
	               double objScore = sim * simObjEn.getValue();
	               String objName = simObjEn.getKey();
	               if (objScoreMap.get(objName) == null) {
	                   objScoreMap.put(objName, objScore);
	               } else {
	                   double totalScore = objScoreMap.get(objName);
	                   objScoreMap.put(objName, totalScore + objScore);
	               }
	           }
	       }
	       List<Entry<String, Double>> enList = new ArrayList<Entry<String, Double>>(objScoreMap.entrySet());
	       Collections.sort(enList, new Comparator<Map.Entry<String, Double>>() {
	           public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
	               Double a = o1.getValue() - o2.getValue();
	               if (a == 0) {
	                   return 0;
	               } else if (a > 0) {
	                   return 1;
	               } else {
	                   return -1;
	               }
	           }
	       });
	       
	       return enList;
	       
   }
    

    /**
     * @Description Ƥ��ѷ���ƶ�  [-1,1], ����ֵԽ�������Խ�󡣵�Ϊ1����-1ʱ������������ػ����
     * @Description ��ȡ�����û�֮���Ƥ��ѷ���ƶ�,���ϵ���ľ���ֵԽ��,��ض�Խ��
     * @author qinshijiang
     * @Date 2013-9-4
     */
    public static double getUserSimilar(Map<String, Integer> pm1, Map<String, Integer> pm2) {
        int n = 0;// ����n  
        int sxy = 0;// ��xy=x1*y1+x2*y2+....xn*yn  
        int sx = 0;// ��x=x1+x2+....xn  
        int sy = 0;// ��y=y1+y2+...yn  
        int sx2 = 0;// ��x2=(x1)2+(x2)2+....(xn)2  
        int sy2 = 0;// ��y2=(y1)2+(y2)2+....(yn)2  
        for (Entry<String, Integer> pme : pm1.entrySet()) {
            String key = pme.getKey();
            Integer x = pme.getValue();
            Integer y = pm2.get(key);
            if (x != null && y != null) {
                n++;
                sxy += x * y;
                sx += x;
                sy += y;
                sx2 += Math.pow(x, 2);
                sy2 += Math.pow(y, 2);
            }
        }
        // p=(��xy-��x*��y/n)/Math.sqrt((��x2-(��x)2/n)(��y2-(��y)2/n));  
        double sd = sxy - sx * sy / n;
        double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));
        return Math.abs(sm == 0 ? 1 : sd / sm);
    }

    /**
     *
     * @Title getEuclidDistance
     * @Class testRecommend
     * @return double
     * @param pm1
     * @param pm2
     * @return
     * @Description��ȡ�����û�֮���ŷ����þ���,����ԽСԽ��
     * @author qinshijiang
     * @Date 2013-9-4
     */
    public static double getEuclidDistance(Map<String, Integer> pm1, Map<String, Integer> pm2) {
        double totalscore = 0.0;
        for (Entry<String, Integer> test : pm1.entrySet()) {
            String key = test.getKey();
            Integer a1 = pm1.get(key);
            Integer b1 = pm2.get(key);
            if (a1 != null && b1 != null) {
                double a = Math.pow(a1 - b1, 2);
                totalscore += Math.abs(a);
            }
        }
        return Math.sqrt(totalscore);
    }
}
