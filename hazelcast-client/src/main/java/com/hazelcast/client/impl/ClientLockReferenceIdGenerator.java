/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.client.impl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This class generates unique (per client) incrementing reference id which is used during locking related requests.
 * The server side uses this id to match if any previous request with the same id was issued and shall not re-do the lock related
 * operation but it shall just return the previous result. Hence, this id identifies the outstanding request sent to the server
 * side for locking operations. Similarly, if the client resends the request to the server for some reason it will use the same
 * reference id to make sure that the operation is not executed more than once at the server side.
 *
 */
public final class ClientLockReferenceIdGenerator {
    private AtomicLong referenceIdCounter = new AtomicLong();

    /**
     *
     * @return A per client unique reference id.
     */
    public long getNextReferenceId() {
        return referenceIdCounter.incrementAndGet();
    }
}
