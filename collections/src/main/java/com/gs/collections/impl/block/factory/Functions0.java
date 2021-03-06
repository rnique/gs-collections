/*
 * Copyright 2015 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.impl.block.factory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.gs.collections.api.bag.MutableBag;
import com.gs.collections.api.block.function.Function0;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.map.MutableMap;
import com.gs.collections.api.set.MutableSet;
import com.gs.collections.impl.block.function.PassThruFunction0;
import com.gs.collections.impl.block.function.checked.CheckedFunction0;
import com.gs.collections.impl.block.function.checked.ThrowingFunction0;
import com.gs.collections.impl.factory.Bags;
import com.gs.collections.impl.factory.Lists;
import com.gs.collections.impl.factory.Maps;
import com.gs.collections.impl.factory.Sets;

public final class Functions0
{
    private static final TrueFunction TRUE_FUNCTION = new TrueFunction();
    private static final FalseFunction FALSE_FUNCTION = new FalseFunction();
    private static final NewFastListFunction<?> NEW_FAST_LIST_FUNCTION = new NewFastListFunction<Object>();
    private static final NewUnifiedSetFunction<?> NEW_UNIFIED_SET_FUNCTION = new NewUnifiedSetFunction<Object>();
    private static final NewHashBagFunction<?> NEW_HASH_BAG_FUNCTION = new NewHashBagFunction<Object>();
    private static final NewUnifiedMapFunction<?, ?> NEW_UNIFIED_MAP_FUNCTION = new NewUnifiedMapFunction<Object, Object>();
    private static final NullFunction<?> NULL_FUNCTION = new NullFunction<Object>();
    private static final AtomicIntegerZeroFunction ATOMIC_INTEGER_ZERO = new AtomicIntegerZeroFunction();
    private static final AtomicLongZeroFunction ATOMIC_LONG_ZERO = new AtomicLongZeroFunction();
    private static final IntegerZeroFunction INTEGER_ZERO = new IntegerZeroFunction();
    private static final BigDecimalZeroFunction BIG_DECIMAL_ZERO = new BigDecimalZeroFunction();
    private static final BigIntegerZeroFunction BIG_INTEGER_ZERO = new BigIntegerZeroFunction();

    private Functions0()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * @since 6.0
     */
    public static Function0<Boolean> getTrue()
    {
        return TRUE_FUNCTION;
    }

    /**
     * @since 6.0
     */
    public static Function0<Boolean> getFalse()
    {
        return FALSE_FUNCTION;
    }

    public static <T> Function0<MutableList<T>> newFastList()
    {
        return (Function0<MutableList<T>>) (Function0<?>) NEW_FAST_LIST_FUNCTION;
    }

    public static <T> Function0<MutableSet<T>> newUnifiedSet()
    {
        return (Function0<MutableSet<T>>) (Function0<?>) NEW_UNIFIED_SET_FUNCTION;
    }

    public static <T> Function0<MutableBag<T>> newHashBag()
    {
        return (Function0<MutableBag<T>>) (Function0<?>) NEW_HASH_BAG_FUNCTION;
    }

    public static <K, V> Function0<MutableMap<K, V>> newUnifiedMap()
    {
        return (Function0<MutableMap<K, V>>) (Function0<?>) NEW_UNIFIED_MAP_FUNCTION;
    }

    public static <T> Function0<T> throwing(ThrowingFunction0<T> throwingFunction0)
    {
        return new ThrowingFunction0Adapter<T>(throwingFunction0);
    }

    public static <T> Function0<T> nullValue()
    {
        return (Function0<T>) NULL_FUNCTION;
    }

    public static <T> Function0<T> value(T t)
    {
        return new PassThruFunction0<T>(t);
    }

    public static Function0<AtomicInteger> zeroAtomicInteger()
    {
        return ATOMIC_INTEGER_ZERO;
    }

    public static Function0<AtomicLong> zeroAtomicLong()
    {
        return ATOMIC_LONG_ZERO;
    }

    /**
     * @since 6.0
     */
    public static Function0<BigDecimal> zeroBigDecimal()
    {
        return BIG_DECIMAL_ZERO;
    }

    /**
     * @since 6.0
     */
    public static Function0<BigInteger> zeroBigInteger()
    {
        return BIG_INTEGER_ZERO;
    }

    private static final class NewFastListFunction<T> implements Function0<MutableList<T>>
    {
        private static final long serialVersionUID = 1L;

        public MutableList<T> value()
        {
            return Lists.mutable.empty();
        }
    }

    private static final class NewUnifiedMapFunction<K, V> implements Function0<MutableMap<K, V>>
    {
        private static final long serialVersionUID = 1L;

        public MutableMap<K, V> value()
        {
            return Maps.mutable.empty();
        }
    }

    private static final class NewUnifiedSetFunction<T> implements Function0<MutableSet<T>>
    {
        private static final long serialVersionUID = 1L;

        public MutableSet<T> value()
        {
            return Sets.mutable.empty();
        }
    }

    private static final class NewHashBagFunction<T> implements Function0<MutableBag<T>>
    {
        private static final long serialVersionUID = 1L;

        public MutableBag<T> value()
        {
            return Bags.mutable.empty();
        }
    }

    private static final class NullFunction<T> implements Function0<T>
    {
        private static final long serialVersionUID = 1L;

        public T value()
        {
            return null;
        }
    }

    private static final class IntegerZeroFunction implements Function0<Integer>
    {
        private static final long serialVersionUID = 1L;

        public Integer value()
        {
            return Integer.valueOf(0);
        }
    }

    private static final class AtomicIntegerZeroFunction implements Function0<AtomicInteger>
    {
        private static final long serialVersionUID = 1L;

        public AtomicInteger value()
        {
            return new AtomicInteger(0);
        }
    }

    private static final class AtomicLongZeroFunction implements Function0<AtomicLong>
    {
        private static final long serialVersionUID = 1L;

        public AtomicLong value()
        {
            return new AtomicLong(0);
        }
    }

    private static final class ThrowingFunction0Adapter<T> extends CheckedFunction0<T>
    {
        private static final long serialVersionUID = 1L;
        private final ThrowingFunction0<T> throwingFunction0;

        private ThrowingFunction0Adapter(ThrowingFunction0<T> throwingFunction0)
        {
            this.throwingFunction0 = throwingFunction0;
        }

        public T safeValue() throws Exception
        {
            return this.throwingFunction0.safeValue();
        }
    }

    private static final class BigDecimalZeroFunction implements Function0<BigDecimal>
    {
        private static final long serialVersionUID = 1L;

        public BigDecimal value()
        {
            return BigDecimal.ZERO;
        }
    }

    private static final class BigIntegerZeroFunction implements Function0<BigInteger>
    {
        private static final long serialVersionUID = 1L;

        public BigInteger value()
        {
            return BigInteger.ZERO;
        }
    }

    private static final class TrueFunction implements Function0<Boolean>
    {
        private static final long serialVersionUID = 1L;

        public Boolean value()
        {
            return Boolean.TRUE;
        }
    }

    private static final class FalseFunction implements Function0<Boolean>
    {
        private static final long serialVersionUID = 1L;

        public Boolean value()
        {
            return Boolean.FALSE;
        }
    }
}
