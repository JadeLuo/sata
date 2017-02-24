import java.util.ArrayList;
import java.util.List;

public class Client {
	public static void main(String[] args) {
		String type = "sz";// sh
		Client c = new Client();
		c.getStockInfo(type);
		System.out.println(""+st.size());
	}

	static List<String> st = new ArrayList<String>();
	int i = 1000000;

	private List<String> getStockInfo(String type) {
		StringBuffer paramsBuf = new StringBuffer();

		paramsBuf.append("http://hq.sinajs.cn/list=");

		int j = i + 500, // 取前100条记录
		skip = "var hq_str_sz1000000=\"\"".length();
		paramsBuf.append(type).append(i);
		while (i < j) {
			i++;
			paramsBuf.append(",").append(type).append(("" + i).substring(1));
		}

		String reqStr = paramsBuf.toString();
		System.out.print(reqStr.length());// get长度
		String result = HttpUtil.get(paramsBuf.toString(), "GBK");
		String[] resultArr = result.split(";");
		for (String record : resultArr) {
			if (record.length() > skip) {
				record = record.substring(1 + record.indexOf("\""));
				System.out.println(record);
				st.add(record);
			}
		}

		if (j < 1000000)
			i = j;
		getStockInfo(type);
		return st;
	}
}
