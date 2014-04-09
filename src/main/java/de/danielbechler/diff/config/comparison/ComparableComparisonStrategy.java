/*
 * Copyright 2013 Daniel Bechler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.danielbechler.diff.config.comparison;

import de.danielbechler.diff.node.DiffNode;

import static de.danielbechler.util.Comparables.isEqualByComparison;

/**
 * @author Daniel Bechler
 */
public class ComparableComparisonStrategy implements ComparisonStrategy
{
	public void compare(final DiffNode node, final Class<?> type, final Object working, final Object base)
	{
		if (Comparable.class.isAssignableFrom(type))
		{
			if (isEqualByComparison((Comparable) working, (Comparable) base))
			{
				node.setState(DiffNode.State.UNTOUCHED);
			}
			else
			{
				node.setState(DiffNode.State.CHANGED);
			}
		}
	}
}
