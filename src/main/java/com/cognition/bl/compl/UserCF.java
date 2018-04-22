package com.cognition.bl.compl;


import java.util.HashMap;  
import java.util.HashSet;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.Set;  
  
/** 
 * �����û���Эͬ�����Ƽ��㷨ʵ�� 
A a b d 
B a c 
C b e 
D c d e 
 * @author Administrator 
 * 
 */  
public class UserCF {  
  
    public static void main(String[] args) {  
        /** 
         * �����û�-->��Ʒ��Ŀ  һ���û���Ӧ�����Ʒ 
         * �û�ID ��ƷID���� 
         *   A      a b d 
         *   B      a c 
         *   C      b e 
         *   D      c d e 
         */  
    	String[] source = new String[]{"A a b d","B a c","C b e","D c d e"};
        //�����û�����  
        int N = 4;  
        int[][] sparseMatrix = new int[N][N];//�����û�ϡ����������û����ƶȼ��㡾���ƶȾ���  
        Map<String, Integer> userItemLength = new HashMap<>();//�洢ÿһ���û���Ӧ�Ĳ�ͬ��Ʒ����  eg: A 3  
        Map<String, Set<String>> itemUserCollection = new HashMap<>();//������Ʒ���û��ĵ��ű� eg: a A B  
        Set<String> items = new HashSet<>();//�����洢��Ʒ����  
        Map<String, Integer> userID = new HashMap<>();//�����洢ÿһ���û����û�IDӳ��  
        Map<Integer, String> idUser = new HashMap<>();//�����洢ÿһ��ID��Ӧ���û�ӳ��  
        System.out.println("Input user--items maping infermation:<eg:A a b d>");  
        for(int i = 0; i < N ; i++){//���δ���N���û� ��������  �Կո���  
            String[] user_item = source[i].split(" ");  
            int length = user_item.length;  
            userItemLength.put(user_item[0], length-1);//eg: A 3  
            userID.put(user_item[0], i);//�û�ID��ϡ���������Ӧ��ϵ  
            idUser.put(i, user_item[0]);  
            //������Ʒ--�û����ű�  
            for(int j = 1; j < length; j ++){  
                if(items.contains(user_item[j])){//����Ѿ�������Ӧ����Ʒ--�û�ӳ�䣬ֱ����Ӷ�Ӧ���û�  
                    itemUserCollection.get(user_item[j]).add(user_item[0]);  
                }else{//���򴴽���Ӧ��Ʒ--�û�����ӳ��  
                    items.add(user_item[j]);  
                    itemUserCollection.put(user_item[j], new HashSet<String>());//������Ʒ--�û����Ź�ϵ  
                    itemUserCollection.get(user_item[j]).add(user_item[0]);  
                }  
            }  
        }  
        System.out.println(itemUserCollection.toString());  
        //�������ƶȾ���ϡ�衿  
        Set<Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();  
        Iterator<Entry<String, Set<String>>> iterator = entrySet.iterator();  
        while(iterator.hasNext()){  
            Set<String> commonUsers = iterator.next().getValue();  
            for (String user_u : commonUsers) {  
                for (String user_v : commonUsers) {  
                    if(user_u.equals(user_v)){  
                        continue;  
                    }  
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;//�����û�u���û�v��������������Ʒ����  
                }  
            }  
        }  
        System.out.println(userItemLength.toString());  
        System.out.println("Input the user for recommendation:<eg:A>");  
        String recommendUser = "B";  
        System.out.println(userID.get(recommendUser));  
        //�����û�֮������ƶȡ����������ԡ�  
        int recommendUserId = userID.get(recommendUser);  
        for (int j = 0;j < sparseMatrix.length; j++) {  
                if(j != recommendUserId){  
                    System.out.println(
                    		idUser.get(recommendUserId)+"--"+idUser.get(j)+"���ƶ�:"+
                    sparseMatrix[recommendUserId][j]/Math.sqrt(
                    		userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j))
                    		)
                    );  
                }  
        }  
          
        //����ָ���û�recommendUser����Ʒ�Ƽ���  
        for(String item: items){//����ÿһ����Ʒ  
            Set<String> users = itemUserCollection.get(item);//�õ�����ǰ��Ʒ�������û�����  
            if(!users.contains(recommendUser)){//������Ƽ��û�û�й���ǰ��Ʒ��������Ƽ��ȼ���  
                double itemRecommendDegree = 0.0;  
                for(String user: users){  
                    itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)]/Math.sqrt(userItemLength.get(recommendUser)*userItemLength.get(user));//�Ƽ��ȼ���  
                }  
                System.out.println("The item "+item+" for "+recommendUser +"'s recommended degree:"+itemRecommendDegree);  
            }  
        }  
    }  
  
}  
