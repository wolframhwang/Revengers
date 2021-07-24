/*
  Map을 이용하여 해결했습니다.
*/
import java.util.HashMap;
class Solution {
    public String[] solution(String[] record) {
        String[] s = record;
        HashMap<String, String> map = new HashMap<>();
		String[] sol = new String[s.length];
		int count = 0;
		for (int i = 0; i < s.length; i++) {
			String[] temps = new String[3];
			temps = s[i].split(" ");
			if (temps[0].equals("Enter")) {
				map.put(temps[1], temps[2]);
				sol[i] = temps[1] + "님이 들어왔습니다.";
			} else if (temps[0].equals("Leave")) {
				sol[i] = temps[1] + "님이 나갔습니다.";
			} else {
				sol[i] = "change";
				count--;
				map.put(temps[1], temps[2]);
			}
		}
		String[] result = new String[s.length + count];
		int tempcnt = 0;
		int tempsize = result.length;
		for (int j = 0; j < tempsize; j++) {
			if (sol[j] == "change") {
				tempcnt++;
				tempsize++;
				continue;
			}
			if (sol[j] == null)
				break;
			String[] temps = sol[j].split("님");
			result[j - tempcnt] = map.get(temps[0]) + "님" + temps[1];
		}

        return result;
    }
}
