package com.bgpark.kotlinspringmysqldemo.learning

import com.github.vertical_blank.sqlformatter.SqlFormatter
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl
import org.junit.jupiter.api.Test

class SqlFormatterTest {

    @Test
    fun formatter() {
        val query = "SELECT t1.brand, SUM(IF(t1.age_band = 'age_band.0', t1.cnt, 0)) AS `age_band.0`, SUM(IF(t1.age_band = 'age_band.19', t1.cnt, 0)) AS `age_band.19`, SUM(IF(t1.age_band = 'age_band.24', t1.cnt, 0)) AS `age_band.24`, SUM(IF(t1.age_band = 'age_band.29', t1.cnt, 0)) AS `age_band.29`, SUM(IF(t1.age_band = 'age_band.34', t1.cnt, 0)) AS `age_band.34`, SUM(IF(t1.age_band = 'age_band.40', t1.cnt, 0)) AS `age_band.40`, SUM(IF(t1.gender = 'gender.F', t1.cnt, 0)) AS `gender.F`, SUM(IF(t1.gender = 'gender.M', t1.cnt, 0)) AS `gender.M`, SUM(IF(t1.gender = 'gender.N', t1.cnt, 0)) AS `gender.N`, SUM(t1.cnt) AS total, SUM(t1.qty) AS quantity FROM ( SELECT o.uid, o.brand, CASE WHEN o.age <= 18 THEN 'age_band.0' WHEN o.age >= 19 AND o.age < 24 THEN 'age_band.19' WHEN o.age >= 24 AND o.age < 29 THEN 'age_band.24' WHEN o.age >= 29 AND o.age < 34 THEN 'age_band.29' WHEN o.age >= 34 AND o.age < 40 THEN 'age_band.34' ELSE 'age_band.40' END AS age_band, CASE WHEN o.gender = 'F' THEN 'gender.F' WHEN o.gender = 'M' THEN 'gender.M' ELSE 'gender.N' END AS gender, SUM(IF(o.ord_state = 5, 1, -1)) AS cnt, SUM(IF(o.ord_state = 5, o.qty, -1*o.qty)) AS qty FROM (SELECT ow.ord_wonga_no, om.uid AS uid, g.brand AS brand, u.age AS age, u.gender AS gender, -- INT(date_format(now(), 'yyyy'))-m.birth1 AS age, -- m.sex AS gender, ow.ord_state AS ord_state, ow.qty AS qty FROM musinsa.bizest.order_opt_wonga ow JOIN musinsa.bizest.order_opt oo ON ow.ord_opt_no = oo.ord_opt_no JOIN musinsa.bizest.order_mst om ON oo.ord_no = om.ord_no JOIN datamart.datamart.users u ON om.uid = u.uid JOIN musinsa.bizest.goods g ON ow.goods_no = g.goods_no -- JOIN musinsa.member.rb_s_mbrdata m ON om.uid = m.memberuid WHERE ow.ord_state IN (5, 60, 61) AND ow.ord_state_date >= date_format(date_add(now(), -365),'yyyyMMdd') AND ow.ord_state_date < date_format(now(),'yyyyMMdd') AND u.gender = 'M' ) o GROUP BY all ) t1 JOIN (SELECT g.brand FROM musinsa.bizest.order_opt_wonga ow JOIN musinsa.bizest.goods g ON ow.goods_no = g.goods_no WHERE ow.ord_state IN (5, 60, 61) AND ow.ut >= date_format(dateadd(HOUR, 9-2, now()), 'yyyy-MM-dd HH:mm:ss') -- utc 에 9시간을 더하고(KST) 2시간 이전 AND ow.ut < date_format(dateadd(HOUR, 9-1, now()), 'yyyy-MM-dd HH:mm:ss') GROUP BY all) t2 ON t1.brand = t2.brand GROUP BY all"
        val result = SqlFormatter.format(query)
        println(result)
    }
}