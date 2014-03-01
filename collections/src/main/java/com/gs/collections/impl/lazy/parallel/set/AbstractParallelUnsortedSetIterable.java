/*
 * Copyright 2014 Goldman Sachs.
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

package com.gs.collections.impl.lazy.parallel.set;

import com.gs.collections.api.annotation.Beta;
import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.function.Function2;
import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.block.predicate.Predicate2;
import com.gs.collections.api.block.procedure.Procedure;
import com.gs.collections.api.multimap.set.MutableSetMultimap;
import com.gs.collections.api.multimap.set.UnsortedSetMultimap;
import com.gs.collections.api.set.ParallelUnsortedSetIterable;
import com.gs.collections.impl.block.factory.Functions;
import com.gs.collections.impl.block.factory.Predicates;
import com.gs.collections.impl.lazy.parallel.AbstractParallelIterable;
import com.gs.collections.impl.multimap.set.SynchronizedPutUnifiedSetMultimap;

@Beta
public abstract class AbstractParallelUnsortedSetIterable<T, B extends UnsortedSetBatch<T>> extends AbstractParallelIterable<T, B> implements ParallelUnsortedSetIterable<T>
{
    @Override
    protected boolean isOrdered()
    {
        return false;
    }

    public ParallelUnsortedSetIterable<T> asUnique()
    {
        return this;
    }

    public ParallelUnsortedSetIterable<T> select(Predicate<? super T> predicate)
    {
        return new ParallelSelectUnsortedSetIterable<T>(this, predicate);
    }

    public <P> ParallelUnsortedSetIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.select(Predicates.bind(predicate, parameter));
    }

    public <S> ParallelUnsortedSetIterable<S> selectInstancesOf(Class<S> clazz)
    {
        throw new UnsupportedOperationException();
    }

    public ParallelUnsortedSetIterable<T> reject(Predicate<? super T> predicate)
    {
        return this.select(Predicates.not(predicate));
    }

    public <P> ParallelUnsortedSetIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.reject(Predicates.bind(predicate, parameter));
    }

    public <V> ParallelUnsortedSetIterable<V> collect(Function<? super T, ? extends V> function)
    {
        return new ParallelCollectUnsortedSetIterable<T, V>(this, function);
    }

    public <P, V> ParallelUnsortedSetIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter)
    {
        return this.collect(Functions.bind(function, parameter));
    }

    public <V> ParallelUnsortedSetIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function)
    {
        return this.select(predicate).collect(function);
    }

    public <V> ParallelUnsortedSetIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
    {
        throw new UnsupportedOperationException();
    }

    public <V> UnsortedSetMultimap<V, T> groupBy(final Function<? super T, ? extends V> function)
    {
        final MutableSetMultimap<V, T> result = SynchronizedPutUnifiedSetMultimap.newMultimap();
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                V key = function.valueOf(each);
                result.put(key, each);
            }
        });
        return result;
    }

    public <V> UnsortedSetMultimap<V, T> groupByEach(final Function<? super T, ? extends Iterable<V>> function)
    {
        final MutableSetMultimap<V, T> result = SynchronizedPutUnifiedSetMultimap.newMultimap();
        this.forEach(new Procedure<T>()
        {
            public void value(T each)
            {
                Iterable<V> keys = function.valueOf(each);
                for (V key : keys)
                {
                    result.put(key, each);
                }
            }
        });
        return result;
    }
}
