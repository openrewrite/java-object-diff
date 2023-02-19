/*
 * Copyright 2023 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.danielbechler.diff.path

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Daniel Bechler
 */
class NodePathValueHolderTest extends Specification {
	def "should allow creation of new instances"() {
		expect:
		  NodePathValueHolder.of(String) instanceof NodePathValueHolder
	}

	@Unroll
	def "should store value '#value' at node path '#nodePath'"() {
		given:
		  def valueHolder = NodePathValueHolder.of(String)
		  valueHolder.put(nodePath, value);

		expect:
		  valueHolder.valueForNodePath(nodePath) == value;

		where:
		  nodePath                | value
		  NodePath.withRoot()     | "foo1"
		  NodePath.with("a")      | "foo2"
		  NodePath.with("a", "b") | "foo3"
	}

	def "should return null for unknown paths"() {
		given:
		  def valueHolder = NodePathValueHolder.of(String)

		expect:
		  valueHolder.valueForNodePath(NodePath.with("a")) == null
	}

	def "should return accumulated values along path"() {
		given:
		  def valueHolder = NodePathValueHolder.of(String)
		  valueHolder.put(NodePath.withRoot(), "foo1")
		  valueHolder.put(NodePath.with("a"), "foo2")
		  valueHolder.put(NodePath.with("a", "b"), "foo3")

		expect:
		  valueHolder.accumulatedValuesForNodePath(NodePath.with("a", "b")) == ["foo1", "foo2", "foo3"]
	}
}
