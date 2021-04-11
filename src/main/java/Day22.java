import util.Util;

import java.util.Arrays;
import java.util.List;

public class Day22 {

    public static void main(String[] args) {
        var input = Util.readStrings();
        Util.submitPart1(part1(input));
        Util.submitPart2(part2(input));
    }

    private static int part2(List<String> input) {
        var bhp = getBossValue(input.get(0));
        var bd = getBossValue(input.get(1));

        List<Spell> spells = getSpells();
        var state = new State(bhp, 50, 500, 0, new int[spells.size()]);
        return best(state, spells, bd, true);
    }

    private static int part1(List<String> input) {
        var bhp = getBossValue(input.get(0));
        var bd = getBossValue(input.get(1));

        List<Spell> spells = getSpells();
        var state = new State(bhp, 50, 500, 0, new int[spells.size()]);
        return best(state, spells, bd, false);
    }

    private static List<Spell> getSpells() {
        return Arrays.asList(
                new Spell(53, 4, 0, 0, 0, 0),
                new Spell(73, 2, 0, 2, 0, 0),
                new Spell(113, 0, 6, 0, 7, 0),
                new Spell(173, 3, 6, 0, 0, 0),
                new Spell(229, 0, 5, 0, 0, 101)
        );
    }

    private static int best(State state, List<Spell> spells, int bd, boolean hard) {
        var newState = new State(state);
        if (hard && --newState.mhp == 0) {
            return -1;
        }
        updateActive(spells, newState);
        if (newState.bhp <= 0) {
            return newState.manaSpent;
        }
        int best = -1;
        for (int i = 0; i < spells.size(); ++i) {
            if (newState.active[i] == 0 && spells.get(i).cost <= newState.mana) {
                int m = best(newState, spells, i, bd, hard);
                if (m > 0 && (best == -1 || m < best)) {
                    best = m;
                }
            }
        }
        return best;
    }

    private static int best(State state, List<Spell> spells, int spellIndex, int bd, boolean hard) {
        var newState = new State(state);
        var spell = spells.get(spellIndex);
        newState.mana -= spell.cost;
        newState.manaSpent += spell.cost;
        if (spell.time > 0) {
            newState.active[spellIndex] = spell.time;
        } else {
            newState.bhp -= spell.damage;
            newState.mhp += spell.heal;
        }
        updateActive(spells, newState);
        if (newState.bhp <= 0) {
            return newState.manaSpent;
        }
        int armour = 0;
        for (int i = 0; i < newState.active.length; ++i) {
            if (newState.active[i] > 0) {
                armour += spells.get(i).armour;
            }
        }
        int abd = Math.max(1, bd-armour);
        newState.mhp -= abd;
        if (newState.mhp <= 0) {
            return -1;
        }
        return best(newState, spells, bd, hard);
    }

    private static void updateActive(List<Spell> spells, State state) {
        for (int i = 0; i < state.active.length; ++i) {
            if (state.active[i] > 0) {
                state.bhp -= spells.get(i).damage;
                state.mhp += spells.get(i).heal;
                state.mana += spells.get(i).plusMana;
                --state.active[i];
            }
        }
    }

    private static int getBossValue(String s) {
        var split = s.split(" ");
        return Integer.parseInt(split[split.length-1]);
    }

    public static class State {
        int bhp, mhp, mana, manaSpent;
        final int[] active;

        public State(int bhp, int mhp, int mana, int manaSpent, int[] active) {
            this.bhp = bhp;
            this.mhp = mhp;
            this.mana = mana;
            this.manaSpent = manaSpent;
            this.active = new int[active.length];
            System.arraycopy(active, 0, this.active, 0, active.length);
        }

        public State(State state) {
            this(state.bhp, state.mhp, state.mana, state.manaSpent, state.active);
        }
    }

    public static class Spell {
        final int cost, damage, time, heal, armour, plusMana;

        public Spell(int cost, int damage, int time, int heal, int armour, int plusMana) {
            this.cost = cost;
            this.damage = damage;
            this.time = time;
            this.heal = heal;
            this.armour = armour;
            this.plusMana = plusMana;
        }
    }
}
