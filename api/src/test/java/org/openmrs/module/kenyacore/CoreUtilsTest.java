/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.kenyacore;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.module.kenyacore.test.TestUtils;

/**
 * Tests for {@link CoreUtils}
 */
public class CoreUtilsTest {

	@Test
	public void integration() {
		new CoreUtils();
	}

	/**
	 * @see CoreUtils#merge(java.util.Collection[])
	 */
	@Test
	public void merge_shouldMergeByNaturalOrder() {
		List<Integer> list1 = Arrays.asList(2, 5, 9);
		List<Integer> list2 = Arrays.asList(1, 3, 10);

		Assert.assertThat(CoreUtils.merge(list1, list2), contains(1, 2, 3, 5, 9, 10));
	}

	/**
	 * @see CoreUtils#earliest(java.util.Date, java.util.Date)
	 * @verifies return null if both dates are null
	 */
	@Test
	public void earliest_shouldReturnNullIfBothDatesAreNull() {
		Assert.assertThat(CoreUtils.earliest(null, null), is(nullValue()));
	}

	/**
	 * @see CoreUtils#earliest(java.util.Date, java.util.Date)
	 * @verifies return non-null date if one date is null
	 */
	@Test
	public void earliest_shouldReturnNonNullIfOneDateIsNull() {
		Date date = TestUtils.date(2001, 3, 22);
		Assert.assertThat(CoreUtils.earliest(null, date), is(date));
		Assert.assertThat(CoreUtils.earliest(date, null), is(date));
	}

	/**
	 * @see CoreUtils#earliest(java.util.Date, java.util.Date)
	 * @verifies return earliest date of two non-null dates
	 */
	@Test
	public void earliest_shouldReturnEarliestDateOfTwoNonNullDates() {
		Date date1 = TestUtils.date(2001, 3, 22);
		Date date2 = TestUtils.date(2010, 2, 16);
		Assert.assertThat(CoreUtils.earliest(date1, date2), is(date1));
		Assert.assertThat(CoreUtils.earliest(date2, date1), is(date1));
	}

	/**
	 * @see CoreUtils#latest(java.util.Date, java.util.Date)
	 * @verifies return null if both dates are null
	 */
	@Test
	public void latest_shouldReturnNullIfBothDatesAreNull() {
		Assert.assertThat(CoreUtils.latest(null, null), is(nullValue()));
	}
	/**
	 * @see CoreUtils#latest(java.util.Date, java.util.Date)
	 * @verifies return non-null date if one date is null
	 */
	@Test
	public void latest_shouldReturnNonNullIfOneDateIsNull() {
		Date date = TestUtils.date(2010, 11, 22);
		Assert.assertThat(CoreUtils.latest(null, date), is(date));
		Assert.assertThat(CoreUtils.latest(date, null), is(date));
	}
	/**
	 * @see CoreUtils#latest(java.util.Date, java.util.Date)
	 * @verifies return latest date of two non-null dates
	 */
	@Test
	public void latest_shouldReturnLatestDateOfTwoNonNullDates() {
		Date date1 = TestUtils.date(2010, 11, 22);
		Date date2 = TestUtils.date(2011, 2, 20);
		Assert.assertThat(CoreUtils.latest(date1, date2), is(date2));
		Assert.assertThat(CoreUtils.latest(date2, date1), is(date2));
	}

	/**
	 * @see CoreUtils#dateAddDays(java.util.Date, int)
	 * @verifies shift the date by the number of days
	 */
	@Test
	public void dateAddDays_shouldShiftDateByNumberOfDays() {
		Assert.assertThat(CoreUtils.dateAddDays(TestUtils.date(2012, 1, 1), 1), is(TestUtils.date(2012, 1, 2)));
		Assert.assertThat(CoreUtils.dateAddDays(TestUtils.date(2012, 1, 1), 31), is(TestUtils.date(2012, 2, 1)));
		Assert.assertThat(CoreUtils.dateAddDays(TestUtils.date(2012, 1, 1), -1), is(TestUtils.date(2011, 12, 31)));
	}
}