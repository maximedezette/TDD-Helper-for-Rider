# TDD-Helper-for-Rider

![Build](https://github.com/maximedezette/TDD-Helper-for-Rider/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/com.github.maximedezette.tddhelperforrider.svg)](https://plugins.jetbrains.com/plugin/com.github.maximedezette.tddhelperforrider)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/com.github.maximedezette.tddhelperforrider.svg)](https://plugins.jetbrains.com/plugin/com.github.maximedezette.tddhelperforrider)

## Description

<!-- Plugin description -->
The purpose of this plugin is to give you the best TDD experience in Rider IDE.

This plugin is currently under development you can expect to see new features coming soon. 
Stay tuned for updates and thank you for your patience and support!

## Features

* Navigate easily from your test and your implementation
* Create a test in the appropriate location from your test subject

Using a single shortcut: `ctrl + shift + T` by default.
<!-- Plugin description end -->

![demo](doc/images/demo.gif)

**This shortcut can already be assigned in Rider please make sure that the only action bind 
to it is `Navigate between test and test subject` or change the default shortcut (see below).**

## Customize

### Change the default shortcut

Go to `Keymap`, search `Navigate between test and test subject` and
then change the keymap.

![reassign-shortcut](doc/images/reassign-shortcut.png)

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
