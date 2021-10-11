package backTracking;

/*
 *  @author Tim
 *  @version 1.0
 */
public class JointProbProblem
{
	private byte x, y;
	private byte count;
	//private byte status[]=new byte[3];

	/*
	 *  @param
	 */
	public JointProbProblem(byte x, byte y)
	{
		this.x = x;
		this.y = y;
	}


	public byte getCount()
	{
		return count;
	}

	/*
	 *  @param
	 */
	public void jointProb(byte i, byte floor, boolean maxStatus, byte[] status)
	{
		if (floor == 3)
		{
			byte sum = 0;
			for (byte j : status)
				sum += j;
			if (sum == y && maxStatus)
			{
				for (byte j : status)
					System.out.print(j);
				count++;
				System.out.print("\n");
			}
			status[floor - 1] = 0;
		}
		else if (promising(floor, status))
		{
			for (byte j = 1; j <= x; j++)
			{
				if (j == x) maxStatus = true;
				status[floor] = j;
				jointProb(j, (byte) (floor + 1), maxStatus, status.clone());
			}
		}
	}

	private boolean promising(byte floor, byte[] status)
	{
		boolean Switch = false;
		byte sum = 0;

		for (byte j : status)
			sum += j;
		if (sum < y)
		{
			if (y >= sum + (3 - floor) && y <= sum + x * (3 - floor)) Switch = true;
		}

		return Switch;
	}
}
