package net.sf.openrocket.gui.figure3d.airflow;



public class NumberToColor {

	private double scale;
	private int[] colorMap; //16*2*4 = 128 elements
	
	public NumberToColor(double scale, int[] map)
	{
		this.scale = scale;
		this.colorMap = map;
	}
	
	public void compute(double number, byte[] colorOut)
	{
		int range = (int)Math.round(Math.floor(number * scale));
		double offset = number*scale - range;
		
		range = range + 8;
		if (range < 0)
		{
			range = 0;
			offset=0;
		}
		if (range > 15)
		{
			range = 15;
			offset = 1.0;
		}
		
		colorOut[0] = (byte)(colorMap[range*8 + 0]*(1.0-offset) + colorMap[range*8 + 4]*offset);
		colorOut[1] = (byte)(colorMap[range*8 + 1]*(1.0-offset) + colorMap[range*8 + 5]*offset);
		colorOut[2] = (byte)(colorMap[range*8 + 2]*(1.0-offset) + colorMap[range*8 + 6]*offset);
		colorOut[3] = (byte)(colorMap[range*8 + 3]*(1.0-offset) + colorMap[range*8 + 7]*offset);
		
	}
	
	public final static NumberToColor RED_GREEN = new NumberToColor(8, new int[] {
			0xFF,0x00,0xFF,0x00,  0xFF,0x20,0x00,0x00,
			0xFF,0x20,0x00,0x00,  0xFF,0x40,0x00,0x00,
			0xFF,0x40,0x00,0x00,  0xFF,0x60,0x00,0x00,
			0xFF,0x60,0x00,0x00,  0xFF,0x80,0x00,0x00,

			0xFF,0x80,0x00,0x00,  0xFF,0xA0,0x00,0x00,
			0xFF,0xA0,0x00,0x00,  0xFF,0xC0,0x00,0x00,
			0xFF,0xC0,0x00,0x00,  0xFF,0xE0,0x00,0x00,
			0xFF,0xE0,0x00,0x00,  0xFF,0xFF,0x00,0x00,

			0xFF,0xFF,0x00,0x00,  0xE0,0xFF,0x00,0x00,
			0xE0,0xFF,0x00,0x00,  0xC0,0xFF,0x00,0x00,
			0xC0,0xFF,0x00,0x00,  0xA0,0xFF,0x00,0x00,
			0xA0,0xFF,0x00,0x00,  0x80,0xFF,0x00,0x00,

			0x80,0xFF,0x00,0x00,  0x60,0xFF,0x00,0x00,
			0x60,0xFF,0x00,0x00,  0x40,0xFF,0x00,0x00,
			0x40,0xFF,0x00,0x00,  0x20,0xFF,0x00,0x00,
			0x20,0xFF,0x00,0x00,  0x00,0xFF,0xFF,0x00
});
}
