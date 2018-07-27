
create SEQUENCE venue_dry_dock_id_sequence INCREMENT BY 1;

create table venue_dry_dock (
  id bigint NOT NULL primary key DEFAULT nextval(
      'venue_dry_dock_id_sequence'::regclass),
  creator_id bigint NOT NULL,
  validstarttime date NOT NULL,
  validendtime date NOT NULL,
  transactiontime timestamp without time zone NOT NULL,
  nexttransactiontime timestamp without time zone NOT NULL,
  venue_id bigint NOT NULL);


ALTER TABLE venue_dry_dock
  ADD FOREIGN KEY (creator_id)
REFERENCES public.system_user(id);


ALTER TABLE venue_dry_dock
  ADD FOREIGN KEY (venue_id)
REFERENCES public.venue(id);


with changed_dry_dock as (SELECT 1, d.value_start, d.value_end, now(),i.nexttransactiontime,
                            i.venue_id
                          FROM public.venue_itinerary_shard i join venue_drydock_periods vd
                              on i.id = vd.venue_itinerary_shard_id join dry_dock_period d
                              on vd.drydock_period_id = d.id
                          where i.nexttransactiontime > now() and i.validendtime>now() order by i.venue_id)
INSERT INTO venue_dry_dock(creator_id, validstarttime, validendtime, transactiontime,
                           nexttransactiontime, venue_id)
  select * from changed_dry_dock;