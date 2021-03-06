/**
 * Copyright (c) 2009-2015, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.impl;

import static org.jooq.impl.DSL.function;
import static org.jooq.impl.DSL.inline;
import static org.jooq.impl.DSL.one;

import java.math.BigDecimal;

import org.jooq.Configuration;
import org.jooq.Field;

/**
 * @author Lukas Eder
 */
class Euler extends AbstractFunction<BigDecimal> {

    /**
     * Generated UID
     */
    private static final long serialVersionUID = -420788300355442056L;

    Euler() {
        super("e", SQLDataType.NUMERIC);
    }

    @Override
    final Field<BigDecimal> getFunction0(Configuration configuration) {
        switch (configuration.family()) {
            /* [pro] xx
            xxxx xxxxxxx
            xxxx xxxx
            xxxx xxxx
            xxxx xxxxx
            xxxx xxxxxxxxx
            xxxx xxxxxxx
            xxxx xxxxxxx
            xxxx xxxxxxxxx
            xxxx xxxxxxxxxx
            xxxx xxxxxxx
            xxxx xxxxxxxx
            xx [/pro] */
            case CUBRID:
            case DERBY:
            case FIREBIRD:
            case H2:
            case HSQLDB:
            case MARIADB:
            case MYSQL:
            case POSTGRES:
                return DSL.exp(one());

            case SQLITE:
                return inline(Math.E, BigDecimal.class);

            // The Euler number doesn't seem to exist in any dialect...
            default:
                return function("e", getDataType());
        }
    }
}
