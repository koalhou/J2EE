/**
 * @author haoxy
 * @createdate 2013年8月30日 上午8:38:13
 * @description 
 */
package com.yutong.axxc.parents.service.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author haoxy
 *
 */
public class AlgorithmUtil
{
	private static Logger log = LoggerFactory.getLogger(AlgorithmUtil.class);
	
	public static double getDistance(double lon1, double lat1, double lon2, double lat2)
	{
		double d2r = Math.PI / 180;
		double distance = 0;

		try
		{
			double dlong = (lon2 - lon1) * d2r;
			double dlat = (lat2 - lat1) * d2r;
			double a = Math.pow(Math.sin(dlat / 2.0), 2) + Math.cos(lat1 * d2r)
					* Math.cos(lat2 * d2r) * Math.pow(Math.sin(dlong / 2.0), 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			distance = 6367 * c * 1000;

		} catch (Exception e)
		{
			log.error("AlgorithmUtil_getDistance error", e);
		}

		return distance;
	}
	

	public static Date roundDate(Date d1)
	{
		Date date = null;
		String s = DateUtil.changeDateTo14String(d1);
		s = "2000-01-01" + " " + s.split(" ")[1];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			date = sdf.parse(s);
		} catch (ParseException e)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(2000, 1, 1, 10, 10, 10);
			date = calendar.getTime();
		}

		return date;
	}

	public static Date getAvgTime(Date d1, Date d2)
	{
		return new Date((long) (d1.getTime() + d2.getTime()) / 2);
	}

	public static int getTimespanMinutes(Date d1, Date d2)
	{
		return (int) Math.abs((d1.getTime() - d2.getTime()) / (60 * 1000));
	}
	
	public static int getTimespanSeconds(Date d1, Date d2)
	{
		return (int) Math.abs((d1.getTime() - d2.getTime()) / (1000));
	}
	
	public static int getTimespanHours(Date d1, Date d2)
	{
		return (int) Math.abs((d1.getTime() - d2.getTime()) / (60 * 60 * 1000));
	}
	
	/*
	 * 使用中值滤波的思想求平均值
	 */
	public static Date getAverageDateMedianFilter(List<Date> dates)
	{
		Date retDate = null;
		
		if(dates != null && dates.size() > 0)
		{
			if(dates.size() == 1)
			{
				return dates.get(0);
			}
			else if(dates.size() == 2)
			{
				return getAvgTime(dates.get(0), dates.get(1));
			}
			else
			{
				try
				{
					List<Long> dateTimes = new ArrayList<Long>();
					
					for(Date date : dates)
					{
						dateTimes.add(date.getTime());
					}
					
					List<Long> newDateTimes = getSampleByMedianFilter(dateTimes);
					
					long sum = 0;
					
					for(long item : newDateTimes)
					{
						sum += item;
					}
					
					long avgResult =  sum / newDateTimes.size();
					
					retDate = new Date(avgResult);
				}
				catch(Exception e)
				{
					log.error("AlgorithmUtil_getAverageDateMedianFilter error", e);
				}
			}
		}
		
		return retDate;
	}
	
	public static List<Long> getSampleByMedianFilter(List<Long> samples)
	{	
		//小于三个就不做了
		if(samples == null || samples.size() < 3)
		{
			return samples;
		}
		else
		{
			try
			{
				//邻域的个数
				int medianSampleCount = samples.size() / 2 + 1;
				List<Long> newSamples = new ArrayList<Long>();
				
				for(int i=0;i<samples.size();i++)
				{
					//定义邻域
					List<Long> medianSample = new ArrayList<Long>();
					
					int count = medianSampleCount;
					int step = 1;
					//先取左边的，再取右边的
					boolean left = true;
					
					medianSample.add(samples.get(i));
					
					while(count-- > 1)
					{
						int index = 0;
						
						if(left)
						{
							index = i - step;
							
							if(index < 0)
							{
								index = samples.size() - Math.abs(index);
							}
														
						}
						else
						{
							index = i + step;
							
							if (index >= samples.size())
							{
								index = index - samples.size();
							}
							
							step++;
						}
						
						left = !left;
						medianSample.add(samples.get(index));
					}
					//排序
					Collections.sort(medianSample);
					
					//取中值
					if(medianSampleCount % 2 == 0) //偶数
					{
						long avg = (medianSample.get(medianSampleCount / 2 - 1) + medianSample.get(medianSampleCount / 2)) / 2;
						newSamples.add(avg);
					}
					else //基数
					{
						newSamples.add(medianSample.get(medianSampleCount / 2));
					}
				}
				
				return newSamples;
			}
			catch(Exception e)
			{
				log.error("AlgorithmUtil_getSampleByMedianFilter error", e);
				
				return samples;
			}
		}		
	}
	

	public static long getAverageLongMedianFilter(List<Long> samples)
	{
		long ret = 0;
		
		if(samples != null && samples.size() > 0)
		{
			if(samples.size() == 1)
			{
				ret = samples.get(0).longValue();
			}
			else if(samples.size() == 2)
			{
				ret = (samples.get(0).longValue() + samples.get(1).longValue()) / 2;
			}
			else
			{
				try
				{
					List<Long> newSamples = getSampleByMedianFilter(samples);
					
					long sum = 0;
					
					for(long item : newSamples)
					{
						sum += item;
					}
					
					ret =  sum / newSamples.size();					
				}
				catch(Exception e)
				{
					log.error("AlgorithmUtil_getAverageLongMedianFilter error", e);
				}
			}
		}
		
		return ret;
	}
}
