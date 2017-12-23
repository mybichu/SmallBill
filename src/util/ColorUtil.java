package util;

import java.awt.Color;

/**
 * 颜色工具
 * 
 * @author 于修彦
 *
 */
public class ColorUtil {
	public static Color blueColor = Color.decode("#3399FF");
	public static Color grayColor = Color.decode("#999999");
	public static Color backgroundColor = Color.decode("#EEEEEE");
	public static Color warningColor = Color.decode("#FF3333");

	/**
	 * 根据百分比改变颜色（绿->红）
	 * 
	 * @param per
	 *            百分比
	 * @return Color
	 */
	public static Color getByPercentage(int per) {
		if (per > 100) {
			per = 100;
		}
		int r = 51;
		int g = 255;
		int b = 51; // 此时（51,255,51）是绿色

		float rate = per / 100f;
		r = (int) (204.0f * rate + 51.0f);
		g = 255 - r + 51;

		return new Color(r, g, b);
	}
}
